package cn.moonshotacademy;

import java.util.Scanner;

public class Controller {
    Scanner s = new Scanner(System.in);
    public Account account;
    public Storage storage;
    public UI ui;

    public Controller(Account account, Storage storage, UI ui){
        this.account = account;
        this.storage = storage;
        this.ui=ui;
        ui.add_output(
            "Instructions",
            "Welcome to the Vending Machine 1.0\r\n"
            + "Instructions:\r\n"
            + "(Please follow the rules below carefully)\r\n"
            + "input 'product': enter product page\r\n"
            + "input 'buy': buy some drinks\r\n"
            + "input 'account': enter account page\r\n"
            + "input 'login': log in your account\r\n"
            + "input 'logout': log out your account\r\n"
            + "input 'password': edit your password\r\n"
        );
        ui.add_output(
            "inquire deal",
            "Please input product id and product number\r\n"
        );
        ui.add_output(
            "inquire discount",
            "Input discount id you want to use in order, input zero if you don't want discount\r\n"
            + "(The order of input will effect how cost is calculated)\r\n"
        );
        ui.add_output("next line", "\r\n");
        ui.add_warning();
    }

    public void init() {
        try {
            ui.output("Instructions");

            String sign = s.nextLine();
            if (sign.equals("product")) {
                storage.display();
            } else if (sign.equals("buy")) {
                this.buy_in();
            } else if (sign.equals("account")) {
                account.display();
            } else if (sign.equals("login")) {
                account.log_in();
            } else if (sign.equals("logout")) {
                account.log_out();
            } else if (sign.equals("password")) {
                account.edit_password();
            } else {
                throw new Exception("undefined command");
            }
        } catch (Exception e) {
            ui.check_warning(e.getMessage());
        } finally {
            init();
        }
    }

    public void buy_in() throws Exception {
        if (!account.check_login()) {
            account.log_in();
        }

        storage.display();

        ui.output("inquire deal");
        String[] in = ui.get_words();

        account.display_sale();
        ui.output("inquire discount");
        int[] salein = ui.get_ints();

        if ((in[0] != null) && (in[1] != null)) {
            ui.print("That will cost " +
            this.account.get_price(Integer.valueOf(in[0]), Integer.valueOf(in[1]), salein)+"\r\n");
            ui.print("Input 'break' to give up, other inputs to confirm\r\n");
            String sign = ui.get_words()[0];
            if(!sign.equals("break")){
                this.account.buy_in(Integer.valueOf(in[0]), Integer.valueOf(in[1]), salein);
            }
            else{
                throw new Exception("none");
            }
        } else {
            throw new Exception("not enough reference");
        }
    }
}
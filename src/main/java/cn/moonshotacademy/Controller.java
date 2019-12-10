package cn.moonshotacademy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("controller")
public class Controller {
    @Autowired
    public UI ui;
    @Autowired
    public Storage storage;
    @Autowired
    public Account account;

    public Controller(){
    }

    public void init() {
        ui.addOutput(
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
        ui.addOutput(
            "inquire deal",
            "Please input product id and product number\r\n"
        );
        ui.addOutput(
            "inquire discount",
            "Input discount id you want to use in order, input zero if you don't want discount\r\n"
            + "(The order of input will effect how cost is calculated)\r\n"
        );
        ui.addOutput("next line", "\r\n");
        ui.addWarning();
        while(true){
            try {
                ui.output("Instructions");

                String sign = ui.getNextLine();
                if (sign.equals("product")) {
                    storage.display();
                } else if (sign.equals("buy")) {
                    this.buyIn();
                } else if (sign.equals("account")) {
                    account.display();
                } else if (sign.equals("login")) {
                    account.logIn();
                } else if (sign.equals("logout")) {
                    account.logOut();
                } else if (sign.equals("password")) {
                    account.editPassword();
                } else {
                    throw new Exception("undefined command");
                }
            } catch (Exception e) {
                ui.checkWarning(e.getMessage());
            }
        }
    }

    public void buyIn() throws Exception {
        if (!account.checkLogin()) {
            account.logIn();
        }

        storage.display();

        ui.output("inquire deal");
        String[] in = ui.getWords();

        account.displaySale();
        ui.output("inquire discount");
        int[] salein = ui.getInts();

        if ((in[0] != null) && (in[1] != null)) {
            ui.print("That will cost " +
            this.account.getPrice(Integer.valueOf(in[0]), Integer.valueOf(in[1]), salein)+"\r\n");
            ui.print("Input 'break' to give up, other inputs to confirm\r\n");
            String sign = ui.getWords()[0];
            if(!sign.equals("break")){
                this.account.buyIn(Integer.valueOf(in[0]), Integer.valueOf(in[1]), salein);
            }
            else{
                throw new Exception("none");
            }
        } else {
            throw new Exception("not enough reference");
        }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }
}
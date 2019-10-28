
package cn.moonshotacademy;

import java.util.Scanner;;

public class UI {
    Scanner s = new Scanner(System.in);
    public Account account;
    public Storage storage;
    public int warning;

    public void init(Account account, Storage storage) {
        this.account = account;
        this.storage = storage;
        System.out.println("Welcome to the Vending Machine 1.0");
        System.out.println("Instrucions:");
        System.out.println("(Please follow the rules below carefully)");
        System.out.println("input 'product': enter product page");
        System.out.println("input 'buy' product_index product_number: buy some drinks");
        System.out.println("input 'account': enter account page");
        System.out.println("input 'login': log in your account");
        System.out.println("input 'logout': log out your account");
        System.out.println("input 'password': edit your password (WITHOUT space)");
        System.out.println();

        String[] in = new String[10];
        int l = 0;
        in[l] = "";
        String word = s.nextLine();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != (char) ' ') {
                in[l] += word.charAt(i);
            } else {
                l++;
                in[l] = "";
            }
        }

        String sign = in[0];
        if (sign.equals("product")) {
            this.display_product();
        } else if (sign.equals("buy")) {
            this.buy_in();
        } else if (sign.equals("account")) {
            this.display_account();
        } else if (sign.equals("login")) {
            this.log_in_account();
        } else if (sign.equals("logout")) {
            this.log_out_account();
            check_warning();
        } else if (sign.equals("password")) {
            this.edit_password();
            check_warning();
        } else {
            this.warning = 2;
            check_UI_warning();
        }
        init(account, storage);
    }

    public void display_product() {
        System.out.println("This is the product list");
        for (int i = 1; i <= storage.length; i++) {
            System.out.print("id:" + Integer.toString(storage.getproduct(i).id));
            System.out.print("  name:" + storage.getproduct(i).name);
            System.out.print("  price:" + Double.toString(storage.getproduct(i).price));
            System.out.println("  remaining:" + Integer.toString(storage.getproduct(i).remain));
        }
        System.out.println();
    }

    public void display_account() {
        if (account.login) {
            System.out.println(account.id);
            System.out.println("Status: loged in");
            System.out.println("remaining: " + account.getremain());
        } else {
            System.out.println("No user has loged in yet");
            System.out.println();
        }
    }

    public void buy_in() {
        if (!account.login) {
            log_in_account();
        }
        if ((in[1] != null) && (in[2] != null)) {
            account.buy_in(Integer.valueOf(in[1]), Integer.valueOf(in[2]));
        } else {
            this.warning = 1;
            check_UI_warning();
        }
        check_warning();
    }

    public void log_in_account() {
        System.out.println("Please log in first");
        System.out.println("ID?");
        String id = s.nextLine();
        System.out.println("Password?");
        String password = s.nextLine();
        account.log_in(id, password);
        check_warning();
    }

    public void log_out_account() {
        account.log_out();
    }

    public void edit_password() {
        if (!account.login) {
            this.log_in_account();
        }
        System.out.println("ID: ");
        String id = s.nextLine();
        System.out.println("New password: ");
        String password1 = s.nextLine();
        System.out.println("Confirm password: ");
        String password2 = s.nextLine();
        account.edit_password(id, password1, password2);
    }

    public void check_warning() {
        if (storage.warning == 1) // Not enough storage
        {
            System.out.println("Don't drink that much. Be healthy.");
            System.out.println();
            return;
        } else if (storage.warning == 2) // wrong arguments
        {
            System.out.println("Read your input again.    Look at what you've wrote.");
            System.out.println();
            return;
        } else if (account.warning == 3) // double login
        {
            System.out.println("You can't log in when logged in, just like you can't die when dead");
            System.out.println();
            return;
        } else if (account.warning == 4) // wrong password
        {
            System.out.println("Are you trying to break in someone else's account?");
            System.out.println();
            return;
        } else if (account.warning == 5) // id not found
        {
            System.out.println("Are you sure this guy really exist?");
            System.out.println();
            return;
        } else if (account.warning == 6) // not enough money
        {
            System.out.println("You have no money here...");
            System.out.println();
            return;
        } else if (account.warning == 7) // log out when logged out
        {
            System.out.println("You can't log out when logged out, just like you can't die when dead");
            System.out.println();
            return;
        } else if (account.warning == 8) // password not successfully confirmed
        {
            System.out.println("Treat your password seriously please");
            System.out.println();
            return;
        }
    }

    public void check_UI_warning() {
        if (this.warning == 1) // not enough reference
        {
            System.out.println("What do you f*** want?");
            System.out.println();
            return;
        } else if (this.warning == 2) // undefined command
        {
            System.out.println("I don't understand");
            System.out.println();
            return;
        }
    }
}
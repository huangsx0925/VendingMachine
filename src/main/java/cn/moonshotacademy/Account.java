package cn.moonshotacademy;

public class Account {
    public String id;
    public String password;
    public Double remain;
    public boolean login;
    public Storage storage;
    public int warning;

    Account(Storage storage) {
        this.storage = storage;
        this.id = "Drinker";
        this.password = "123456";
        this.remain = 300.00;
        this.login = false;
    }

    public double check_remain() {
        return this.remain;
    }

    public void buy_in(int product_id, int number) {
        if (storage.sell_out(product_id, number)) {
            if (this.remain - number * storage.products[product_id].price >= 0) {
                this.remain -= number * storage.products[product_id].price;
                System.out.println("Sale Done!");
            } else {
                this.warning = 6;
                System.out.println("Sale Fail!");
            }
        }
    }

    public void log_in(String id, String password) {
        if (!this.login) {
            if (id.equals(this.id)) {
                if (password.equals(this.password)) {
                    this.login = true;
                    System.out.println("Log in Done!");
                } else {
                    this.warning = 4;
                }
            } else {
                this.warning = 5;
            }
        } else {
            this.warning = 3;
        }
        // UI.display_account();
    }

    public void log_out() {
        if (this.login) {
            this.login = false;
        } else {
            this.warning = 7;
        }
    }

    public void edit_password(String id, String password1, String password2) {
        if (password1.equals(password2)) {
            this.password = password1;
        } else {
            this.warning = 8;
        }
    }

    public Double getremain() {
        return this.remain;
    }
}
package cn.moonshotacademy;

public class Account {
    private String id;
    private String password;
    private Double remain;
    private boolean login;
    private Storage storage;
    private Sale[] sales = new Sale[10];
    private UI ui;

    public Account(Storage storage, UI ui) {
        this.storage = storage;
        this.ui = ui;
        this.id = "Drinker";
        this.password = "123456";
        this.remain = 300.00;
        this.login = false;
        {
            Pair[] p = new Pair[2];
            p[0] = new Pair(0.0, 0.0);
            p[1] = new Pair(10000.0, 10000.0);
            this.sales[0] = new Sale("default", p, 10000);
        }
        {
            Pair[] p = new Pair[2];
            p[0] = new Pair(0.0, 0.0);
            p[1] = new Pair(10000.0, 8000.0);
            this.sales[1] = new Sale("20% off", p, 3);
        }
        {
            Pair[] p = new Pair[3];
            p[0] = new Pair(0.0, 0.0);
            p[1] = new Pair(100.0, 100.0);
            p[2] = new Pair(10000.0, 7525.0);
            this.sales[2] = new Sale("25% off for the part over 100", p, 3);
        }
        {
            Pair[] p = new Pair[4];
            p[0] = new Pair(0.0, 0.0);
            p[1] = new Pair(50.0, 50.0);
            p[2] = new Pair(50.0, 40.0);
            p[3] = new Pair(10000.0, 9990.0);
            this.sales[3] = new Sale("minus 10 after reaching 50", p, 3);
        }
    }

    public double check_remain() {
        return this.remain;
    }

    public void buy_in(int product_id, int number, int[] sale_num) throws Exception {
        Product product = this.storage.get_product(product_id);
        Sale[] tmp_sales = new Sale[sale_num.length];
        for (int i = 0; i < tmp_sales.length; i++) {
            tmp_sales[i] = this.sales[sale_num[i]];
        }
        Double price = this.get_discount(number * product.price, tmp_sales);
        if (this.remain >= price) {
            storage.sell_out(product_id, number);
            for (Sale sale : tmp_sales) {
                sale.consume();
            }
            this.remain -= price;
            System.out.println("Sale Done!");
        } else {
            System.out.println("Sale Fail!");
            throw new Exception("not enough money");
        }
    }

    public Double get_price(int product_id, int number, int[] sale_num) throws Exception {
        Product product = this.storage.get_product(product_id);
        Sale[] tmp_sales = new Sale[sale_num.length];
        for (int i = 0; i < tmp_sales.length; i++) {
            tmp_sales[i] = this.sales[sale_num[i]];
        }
        Double price = this.get_discount(number * product.price, tmp_sales);
        return price;
    }

    public void log_in() throws Exception {
        if(this.login){
            throw new Exception("double login");
        }
        else{
            ui.print("Please log in first\r\n");
            ui.print("ID?\r\n");
            String id = ui.get_line();
            ui.print("Password?\r\n");
            String password = ui.get_line();
            this.check(id, password);
            this.set_login();
            ui.print("Log in Done!");
        }
    }

    public void log_out() throws Exception {
        if (this.login) {
            this.login = false;
        } else {
            throw new Exception("double logout");
        }
    }

    public void edit_password() throws Exception {
        ui.print("ID: \r\n");
        String id = ui.get_line();
        ui.print("Old password: \r\n");
        String old_password = ui.get_line();
        this.check(id, old_password);

        ui.print("New password: ");
        String password1 = ui.get_line();
        ui.print("Confirm password: ");
        String password2 = ui.get_line();
        if (password1.equals(password2)) {
            this.password = password1;
        } else {
            throw new Exception("password not successfully confirmed");
        }
    }

    public Double get_remain() {
        return this.remain;
    }

    public Boolean check_login() {
        return this.login;
    }

    public String get_id() {
        return this.id;
    }

    public void display_sale() {
        System.out.println("This is the discount list");
        for (int i = 1; i < this.sales.length; i++) {
            if (this.sales[i] == null) {
                break;
            } else {
                this.sales[i].display_sale();
            }
        }
    }

    public Double get_discount(Double original_price, Sale[] sales) {
        Double ans = original_price;
        for (int i = 0; i < sales.length; i++) {
            ans = sales[i].get_price(ans);
        }
        return ans;
    }

    public void set_login() throws Exception {
        this.login = true;
    }

    public void display() {
        if (this.check_login()) {
            System.out.println(this.get_id());
            System.out.println("remaining: " + this.get_remain());
            this.display_sale();
            System.out.println();
        } else {
            System.out.println("No user has logged in yet");
            System.out.println();
        }
    }

    /**
     * Throw error if id or password not fit
     * @param id
     * @param password
     * @throws Exception
     */
    public void check(String id, String password) throws Exception {
        if (id.equals(this.id)) {
            if (password.equals(this.password)) {
                return;
            } else {
                throw new Exception("wrong password");
            }
        } else {
            throw new Exception("id not found");
        }
    }
}
package cn.moonshotacademy;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("account")
public class Account {
    @Autowired
    private UI ui;
    @Autowired
    private Storage storage;

    @Value("Drinker")
    private String id;
    @Value("123456")
    private String password;
    @Value("300.00")
    private Double remain;
    @Value("false")
    private boolean login;
    private ArrayList<Sale> sales = new ArrayList<Sale>();
    // private ArrayList<String> record = new ArrayList<String>();

    public Account() {
    }

    @PostConstruct
    public void init() {
        for(int i=0;i<4;i++){
            this.sales.add(new Sale());
            this.sales.get(i).setId(i);
            this.sales.get(i).setUi(ui);
        }
        {
            Pair[] p = new Pair[2];
            p[0] = new Pair(0.0, 0.0);
            p[1] = new Pair(10000.0, 10000.0);
            this.sales.get(0).setName("default");
            this.sales.get(0).setPrice(p);
            this.sales.get(0).setRemain(10000);
        }
        {
            Pair[] p = new Pair[2];
            p[0] = new Pair(0.0, 0.0);
            p[1] = new Pair(10000.0, 8000.0);
            this.sales.get(1).setName("20% off");
            this.sales.get(1).setPrice(p);
            this.sales.get(1).setRemain(3);
        }
        {
            Pair[] p = new Pair[3];
            p[0] = new Pair(0.0, 0.0);
            p[1] = new Pair(100.0, 100.0);
            p[2] = new Pair(10000.0, 7525.0);
            this.sales.get(2).setName("25% off for the part over 100");
            this.sales.get(2).setPrice(p);
            this.sales.get(2).setRemain(3);
        }
        {
            Pair[] p = new Pair[4];
            p[0] = new Pair(0.0, 0.0);
            p[1] = new Pair(50.0, 50.0);
            p[2] = new Pair(50.0, 40.0);
            p[3] = new Pair(10000.0, 9990.0);
            this.sales.get(3).setName("minus 10 after reaching 50");
            this.sales.get(3).setPrice(p);
            this.sales.get(3).setRemain(3);
        }
    }

    public double checkRemain() {
        return this.remain;
    }

    public void buyIn(int product_id, int number, int[] sale_num) throws Exception {
        Product product = this.storage.getProduct(product_id);
        Sale[] tmp_sales = new Sale[sale_num.length];
        for (int i = 0; i < tmp_sales.length; i++) {
            tmp_sales[i] = this.sales.get(sale_num[i]);
        }
        Double price = this.getDiscount(number * product.getPrice(), tmp_sales);
        if (this.remain >= price) {
            storage.sellOut(product_id, number);
            for (Sale sale : tmp_sales) {
                sale.consume();
            }
            this.remain -= price;
            ui.print("Sale Done!\r\n");
        } else {
            ui.print("Sale Fail!\r\n");
            throw new Exception("not enough money");
        }
    }

    public Double getPrice(int product_id, int number, int[] sale_num) throws Exception {
        Product product = this.storage.getProduct(product_id);
        Sale[] tmp_sales = new Sale[sale_num.length];
        for (int i = 0; i < tmp_sales.length; i++) {
            tmp_sales[i] = this.sales.get(sale_num[i]);
        }
        Double price = this.getDiscount(number * product.getPrice(), tmp_sales);
        return price;
    }

    public void logIn() throws Exception {
        if(this.login){
            throw new Exception("double login");
        }
        else{
            ui.print("Please log in first\r\n");
            ui.print("ID?\r\n");
            String id = ui.getNextLine();
            ui.print("Password?\r\n");
            String password = ui.getNextLine();
            this.check(id, password);
            this.setLogin();
            ui.print("Log in Done!\r\n");
        }
    }

    public void logOut() throws Exception {
        if (this.login) {
            this.login = false;
        } else {
            throw new Exception("double logout");
        }
    }

    public void editPassword() throws Exception {
        ui.print("ID: \r\n");
        String id = ui.getNextLine();
        ui.print("Old password: \r\n");
        String old_password = ui.getNextLine();
        this.check(id, old_password);

        ui.print("New password: ");
        String password1 = ui.getNextLine();
        ui.print("Confirm password: ");
        String password2 = ui.getNextLine();
        if (password1.equals(password2)) {
            this.password = password1;
        } else {
            throw new Exception("password not successfully confirmed");
        }
    }

    public Double getRemain() {
        return this.remain;
    }

    public Boolean checkLogin() {
        return this.login;
    }

    public String getId() {
        return this.id;
    }

    public void displaySale() {
        ui.print("This is the discount list\r\n");
        for (int i = 1; i < this.sales.size(); i++) {
            this.sales.get(i).display();
        }
    }

    public Double getDiscount(Double original_price, Sale[] sales) {
        Double ans = original_price;
        for (int i = 0; i < sales.length; i++) {
            ans = sales[i].get_price(ans);
        }
        return ans;
    }

    public void setLogin() throws Exception {
        this.login = true;
    }

    public void display() {
        if (this.checkLogin()) {
            ui.print(this.getId()+"\r\n");
            ui.print("remaining: " + this.getRemain()+"\r\n");
            this.displaySale();
            ui.print("\r\n");
        } else {
            ui.print("No user has logged in yet\r\n\r\n");
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

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }
}
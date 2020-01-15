package cn.moonshotacademy.vendingmachinebysamson.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.moonshotacademy.vendingmachinebysamson.dao.AccountDAO;
import cn.moonshotacademy.vendingmachinebysamson.dao.SaleDAO;
import cn.moonshotacademy.vendingmachinebysamson.pojo.AccountCategory;

@Service
@Component("account")
public class Account {
    @Autowired
    private UI ui;
    @Autowired
    private Storage storage;
    @Autowired
    public AccountDAO accountDAO;
    @Autowired
    public SaleDAO saleDAO;

    private String id;
    private String password;
    private Double remain;
    private boolean login;
    private ArrayList<Sale> sales = new ArrayList<Sale>();
    // private ArrayList<String> record = new ArrayList<String>();

    public Account() {
    }

    @PostConstruct
    public void init() throws Exception {
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
            ui.addResult("Sale Done!");
        } else {
            ui.addResult("Sale Fail!");
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
        ui.addResult("login_typesign_");
        if (this.login) {
            throw new Exception("double login");
        } else {
            // ui.addResult("Please log in first\r\n");
            // ui.addResult("ID?\r\n");
            String id = ui.getRequest();
            // ui.addResult("Password?\r\n");
            String password = ui.getRequest();
            this.check(id, password);
            this.setLogin();
            ui.addResult("Log in Done!");
        }
    }

    public void logOut() throws Exception {
        ui.addResult("logout_typesign_");
        if (this.login) {
            this.login = false;
            ui.addResult("Log out Done!");
        } else {
            throw new Exception("double logout");
        }
    }

    public void editPassword() throws Exception {
        ui.addResult("ID: \r\n");
        String id = ui.getRequest();
        ui.addResult("Old password: \r\n");
        String old_password = ui.getRequest();
        this.check(id, old_password);

        ui.addResult("New password: ");
        String password1 = ui.getRequest();
        ui.addResult("Confirm password: ");
        String password2 = ui.getRequest();
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
        for (int i = 0; i < this.sales.size(); i++) {
            this.sales.get(i).setUi(ui);
            this.sales.get(i).display();
            if (i < this.sales.size() - 1) {
                ui.addResult("_salelistsplit_");
            }
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
        ui.addResult("account_typesign_");
        if (this.checkLogin()) {
            ui.addResult(this.getId() + "_accountsplit_");
            ui.addResult("remaining: " + this.getRemain() + "_accountsplit_");
            this.displaySale();
        } else {
            ui.addResult("nologin");
        }
    }

    /**
     * Throw error if id or password not fit
     * 
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

    public void setSales(String salestr) throws Exception {
        ArrayList<Sale> ret = new ArrayList<Sale>();
        String[] saleid = salestr.split(" ");
        for (String string : saleid) {
            int id = Integer.parseInt(string);
            Sale tmpsale = new Sale();
            tmpsale.setSaleDAO(saleDAO);
            Sale newSale = tmpsale.getSaleById(id);
            boolean flag = true;
            for (Sale sale : ret) {
                if (sale.getName() == newSale.getName()) {
                    sale.setRemain(sale.getRemain() + 1);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                newSale.setRemain(1);
                ret.add(newSale);
            }
        }
        this.sales = ret;
    }

    public Account getAccountByName(String id) throws Exception {
        List<AccountCategory> accountList = accountDAO.findAll();
        for (AccountCategory accountCategory : accountList) {
            String str = accountCategory.getId();
            if (str.equals(id)) {
                Account account = new Account();
                account.setSaleDAO(saleDAO);
                account.setId(id);
                account.setLogin(false);
                account.setPassword(accountCategory.getPassword());
                account.setRemain(accountCategory.getRemain());
                account.setSales(accountCategory.getSalestr());
                return account;
            }
        }
        return null;
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public SaleDAO getSaleDAO() {
        return saleDAO;
    }

    public void setSaleDAO(SaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }

    // public SaleDAO getSaleDAO() {
    // return saleDAO;
    // }

    // public void setSaleDAO(SaleDAO saleDAO) {
    // this.saleDAO = saleDAO;
    // }
}
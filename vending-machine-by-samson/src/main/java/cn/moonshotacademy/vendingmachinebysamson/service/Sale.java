package cn.moonshotacademy.vendingmachinebysamson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moonshotacademy.vendingmachinebysamson.dao.SaleDAO;
import cn.moonshotacademy.vendingmachinebysamson.pojo.SaleCategory;

@Service
public class Sale {
    private int id;
    private String name;
    private Pair[] price;
    private int remain;
    @Autowired
    private UI ui;
    public SaleDAO saleDAO;

    public Sale() {
    }

    public void consume() throws Exception {
        if (this.remain <= 0) {
            throw new Exception("not enough discount");
        }
        if (!this.name.equals("default")) {
            this.remain--;
        }
    }

    public void display() {
        ui.addResult("id:" + Integer.toString(this.id) + "_salesplit_");
        ui.addResult(" name:" + this.name + "_salesplit_");
        ui.addResult(" remaining:" + Integer.toString(this.remain));
    }

    public Double get_price(Double original_price) {
        Pair[] p = new Pair[this.price.length + 1];
        for (int i = 0; i < this.price.length + 1; i++) {
            p[i] = this.price[i];
            if (p[i].x > original_price) {
                p[i + 1] = p[i];
                p[i] = new Pair(original_price, -1.0);
                i++;
            }
        }
        Pair pre = new Pair(0.0, 0.0);
        Pair pos = new Pair(1000.0, 1000.0);
        for (int i = 1; i < p.length; i++) {
            if (p[i].y < 0) {
                pre = p[i - 1];
                pos = p[i + 1];
            }
        }
        return pre.y + (pos.y - pre.y) / (pos.x - pre.x) * (original_price - pre.x);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pair[] getPrice() {
        return price;
    }

    public void setPrice(Pair[] price) {
        this.price = price;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public void setPrice(String xstr, String ystr) throws Exception {
        String[] xstrs = xstr.split(" ");
        String[] ystrs = ystr.split(" ");
        if (xstrs.length != ystrs.length) {
            throw new Exception("Unexpected data from datasource");
        }
        Pair[] pairs = new Pair[xstrs.length];
        for (int i = 0; i < xstrs.length; i++) {
            Double x = Double.parseDouble(xstrs[i]);
            Double y = Double.parseDouble(ystrs[i]);
            Pair pair = new Pair(x, y);
            pairs[i] = pair;
        }
        this.setPrice(pairs);
    }

    public Sale getSaleById(int id) throws Exception {
        List<SaleCategory> saleList = saleDAO.findAll();
        for (SaleCategory saleCategory : saleList) {
            if (saleCategory.getId() == id) {
                Sale sale = new Sale();
                sale.setId(id);
                sale.setName(saleCategory.getName());
                sale.setPrice(saleCategory.getXstr(), saleCategory.getYstr());
                return sale;
            }
        }
        return null;
    }

    public SaleDAO getSaleDAO() {
        return saleDAO;
    }

    public void setSaleDAO(SaleDAO saleDAO) {
        this.saleDAO = saleDAO;
    }
}
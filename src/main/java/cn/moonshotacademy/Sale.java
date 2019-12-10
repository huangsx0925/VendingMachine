package cn.moonshotacademy;

import org.springframework.beans.factory.annotation.Autowired;

public class Sale {
    private int id;
    private String name;
    private Pair[] price;
    private int remain;
    @Autowired
    private UI ui;

    public Sale() {
    }

    public void consume() throws Exception {
        if (this.remain <= 0) {
            throw new Exception("not enough discount");
        }
        this.remain--;
    }

    public void display() {
        ui.print("id:" + this.id);
        ui.print(" name:" + this.name);
        ui.print(" remaining:" + this.remain + "\r\n");
    }

    public Double get_price(Double original_price) {
        Pair[] p = new Pair[this.price.length + 1];
        for (int i = 0; i < this.price.length + 1; i++) {
            p[i] = this.price[i];
            if (p[i].x > original_price) {
                p[i+1]=p[i];
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
}
package cn.moonshotacademy;

import org.springframework.beans.factory.annotation.Autowired;

public class Product {
    private int id;
    private String name;
    private Double price;
    private int remain;
    @Autowired
    private UI ui;

    public Product() {
    }

    /**
     * Display all category of products
     */
    public void display() {
        ui.print("id:" + this.id);
        ui.print("  name:" + this.name);
        ui.print("  price:" + this.price);
        ui.print("  remaining:" + this.remain + "\r\n");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public void setUi(UI ui) {
        this.ui = ui;
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

    public Double getPrice() {
        return price;
    }

    public int getRemain() {
        return remain;
    }

    public UI getUi() {
        return ui;
    }
}
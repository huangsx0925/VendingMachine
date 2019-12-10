package cn.moonshotacademy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("storage")
public class Storage {
    public int length;
    public List<Product> products = new ArrayList<Product>();
    @Autowired
    public UI ui;

    public Storage() {
    }

    @PostConstruct
    public void init() {
        this.length = 6;
        for (int i = 0; i < this.length; i++) {
            this.products.add(new Product());
            this.products.get(i).setId(i + 1);
            this.products.get(i).setUi(ui);
            this.products.get(i).setRemain(100);
        }
        this.products.get(0).setName("water");
        this.products.get(0).setPrice(2.50);
        this.products.get(1).setName("coffee");
        this.products.get(1).setPrice(15.00);
        this.products.get(2).setName("milk");
        this.products.get(2).setPrice(6.33);
        this.products.get(3).setName("tea");
        this.products.get(3).setPrice(5.66);
        this.products.get(4).setName("soda");
        this.products.get(4).setPrice(4.00);
        this.products.get(5).setName("soup");
        this.products.get(5).setPrice(30.00);
    }

    public void sellOut(int productId, int number) throws Exception {
        int id = productId - 1;
        if (this.products.get(id).getRemain() < number) {
            throw new Exception("Not enough storage");
        } else {
            this.products.get(id).setRemain(this.products.get(id).getRemain() - number);
        }
    }

    public Product getProduct(int id) throws Exception {
        if ((id > 0) && (id <= this.length)) {
            return this.products.get(id - 1);
        } else {
            throw new Exception("wrong arguments");
        }
    }

    public void display() {
        ui.print("This is the product list\r\n");
        for (int i = 0; i < this.length; i++) {
            this.products.get(i).display();
        }
        ui.print("\r\n");
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
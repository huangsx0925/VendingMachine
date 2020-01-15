package cn.moonshotacademy.vendingmachinebysamson.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import cn.moonshotacademy.vendingmachinebysamson.dao.ProductDAO;

@Service
@Component("storage")
public class Storage {
    public int length;
    public List<Product> products = new ArrayList<Product>();
    @Autowired
    public UI ui;
    @Autowired
    public ProductDAO productDAO;

    public Storage() {
    }

    @PostConstruct
    public void init() {
        this.length = 6;
        for (int i = 0; i < this.length; i++) {
            Product product = new Product();
            product.setProductDAO(productDAO);
            product = product.getProductById(i+1);
            product.setUi(ui);
            this.products.add(product);
        }
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
        // ui.addResult("This is the product list\r\n");
        ui.addResult("product_typesign_");
        for (int i = 0; i < this.length; i++) {
            this.products.get(i).display();
            if(i<this.length-1){
                ui.addResult("_storagesplit_");
            }
        }
        // ui.addResult("\r\n");
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
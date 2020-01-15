package cn.moonshotacademy.vendingmachinebysamson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moonshotacademy.vendingmachinebysamson.dao.ProductDAO;
import cn.moonshotacademy.vendingmachinebysamson.pojo.ProductCategory;

@Service
public class Product {
    private int id;
    private String name;
    private Double price;
    private int remain;
    @Autowired
    private UI ui;
    private ProductDAO productDAO;

    public Product() {
    }

    /**
     * Display all category of products
     */
    public void display() {
        ui.addResult("id:" + this.id + "_productsplit_");
        ui.addResult("name:" + this.name + "_productsplit_");
        ui.addResult("price:" + this.price + "_productsplit_");
        ui.addResult("remaining:" + this.remain);
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

    public Product getProductById(int id) {
        List<ProductCategory> ProductList = productDAO.findAll();
        for (ProductCategory productCategory : ProductList) {
            if (productCategory.getId() == id) {
                Product ret = new Product();
                ret.setId(productCategory.getId());
                ret.setName(productCategory.getName());
                ret.setPrice(productCategory.getPrice());
                ret.setRemain(productCategory.getRemain());
                return ret;
            }
        }
        return null;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }

    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
}
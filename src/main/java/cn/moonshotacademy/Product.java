package cn.moonshotacademy;

public class Product {
    public int id;
    public String name;
    public Double price;
    public int remain;

    /**
     * Constructor of class Product
     * @param id id of such product
     * @param name name of such product
     * @param price price of such product
     * @param remain remaining number of such product
     */
    public Product(int id, String name, Double price, int remain) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.remain = remain;
    }

    /**
     * Display all category of products
     */
    public void display() {
        System.out.print("id:" + this.id);
        System.out.print("  name:" + this.name);
        System.out.print("  price:" + this.price);
        System.out.println("  remaining:" + this.remain);
    }
}
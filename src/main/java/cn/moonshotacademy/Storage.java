package cn.moonshotacademy;

public class Storage {
    public int length;
    public Product[] products = new Product[100];

    public Storage() {
        this.length = 6;
        this.products[1] = new Product(1, "water", 2.50, 100);
        this.products[2] = new Product(2, "coffee", 15.00, 100);
        this.products[3] = new Product(3, "milk", 6.33, 100);
        this.products[4] = new Product(4, "tea", 5.66, 100);
        this.products[5] = new Product(5, "soda", 4.00, 100);
        this.products[6] = new Product(6, "soup", 30.00, 100);
    }

    public void sell_out(int id, int number) throws Exception {
        if (this.products[id].remain < number) {
            throw new Exception("Not enough storage");
        } else {
            this.products[id].remain -= number;
        }
    }

    public Product get_product(int id) throws Exception {
        if ((id > 0) && (id <= this.length)) {
            return this.products[id];
        } else {
            throw new Exception("wrong arguments");
        }
    }

    public void display() {
        System.out.println("This is the product list");
        for (int i = 1; i <= this.length; i++) {
            this.products[i].display();
        }
        System.out.println();
    }
}
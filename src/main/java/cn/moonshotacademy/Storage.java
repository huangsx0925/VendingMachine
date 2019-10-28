package cn.moonshotacademy;

public class Storage {
    public int length;
    public Product[] products = new Product[100];
    public int warning;

    public Storage() {
        this.length = 6;
        this.products[1] = new Product(1, "water", 2.50, 100);
        this.products[2] = new Product(2, "coffee", 15.00, 100);
        this.products[3] = new Product(3, "milk", 6.33, 100);
        this.products[4] = new Product(4, "tea", 5.66, 100);
        this.products[5] = new Product(5, "soda", 4.00, 100);
        this.products[6] = new Product(6, "soup", 30.00, 100);
    }

    // Use this carefully. It needs to ensure enough remain products.
    public void export_remain(int id, int number) {
        if ((id > 0) && (id <= this.length)) {
            products[id].remain -= number;
        } else {
            warning = 2;
        }
    }

    public boolean sell_out(int id, int number) {
        if (products[id].remain < number) {
            this.warning = 1;
            return false;
        } else {
            this.export_remain(id, number);
            return true;
        }
    }

    public Product getproduct(int id) {
        return products[id];
    }
}
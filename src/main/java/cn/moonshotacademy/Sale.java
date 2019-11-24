package cn.moonshotacademy;

public class Sale {
    private String name;
    private Pair[] price;
    private int remain;

    public Sale(String name, Pair[] price, int remain) {
        this.name = name;
        this.price = price;
        this.remain = remain;
    }

    public String get_name() {
        return this.name;
    }

    public int get_remain() {
        return this.remain;
    }

    public void consume() throws Exception {
        if (this.remain <= 0) {
            throw new Exception("not enough discount");
        }
        this.remain--;
    }

    public void display_sale() {
        System.out.print(this.name + " ");
        System.out.println("remaining:" + this.remain);
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
}
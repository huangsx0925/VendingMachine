package cn.moonshotacademy;

public class Product
{
    public int id;
    public String name;
    public Double price;
    public int remain;
    Product(int id, String name, Double price, int remain)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.remain = remain;
    }
}
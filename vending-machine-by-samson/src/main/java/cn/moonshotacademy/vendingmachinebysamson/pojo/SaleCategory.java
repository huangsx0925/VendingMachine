package cn.moonshotacademy.vendingmachinebysamson.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale")
public class SaleCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "xstr")
    private String xstr;
    @Column(name = "ystr")
    private String ystr;

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

    public String getXstr() {
        return xstr;
    }

    public void setXstr(String xstr) {
        this.xstr = xstr;
    }

    public String getYstr() {
        return ystr;
    }

    public void setYstr(String ystr) {
        this.ystr = ystr;
    }
}
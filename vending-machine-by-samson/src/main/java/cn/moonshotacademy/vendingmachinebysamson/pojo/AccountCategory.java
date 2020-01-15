package cn.moonshotacademy.vendingmachinebysamson.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class AccountCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "name")
    private String id;
    @Column(name = "password")
    private String password;
    @Column(name = "remain")
    private Double remain;
    @Column(name = "salestr")
    private String salestr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getRemain() {
        return remain;
    }

    public void setRemain(Double remain) {
        this.remain = remain;
    }

    public String getSalestr() {
        return salestr;
    }

    public void setSalestr(String salestr) {
        this.salestr = salestr;
    }
}
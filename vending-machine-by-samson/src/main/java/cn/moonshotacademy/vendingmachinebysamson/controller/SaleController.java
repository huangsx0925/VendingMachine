package cn.moonshotacademy.vendingmachinebysamson.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import cn.moonshotacademy.vendingmachinebysamson.Sale;
import cn.moonshotacademy.vendingmachinebysamson.dao.SaleDAO;
import cn.moonshotacademy.vendingmachinebysamson.pojo.SaleCategory;

@RestController
// @RestController
public class SaleController {
    @Autowired
    SaleDAO saleDAO;

    // @RequestMapping("/sale")
    // public Sale getSaleById(@RequestParam("id") int id) throws Exception {
    //     List<SaleCategory> saleList = saleDAO.findAll();
    //     for (SaleCategory saleCategory : saleList) {
    //         if(saleCategory.getId()==id){
    //             Sale sale = new Sale();
    //             sale.setId(id);
    //             sale.setName(saleCategory.getName());
    //             sale.setPrice(saleCategory.getXstr(),saleCategory.getYstr());
    //             return sale;
    //         }
    //     }
    //     return null;
    // }
}
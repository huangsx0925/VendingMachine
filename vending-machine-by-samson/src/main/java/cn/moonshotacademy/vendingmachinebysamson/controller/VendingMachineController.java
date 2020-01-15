package cn.moonshotacademy.vendingmachinebysamson.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.moonshotacademy.vendingmachinebysamson.service.ServiceController;

// import cn.moonshotacademy.vendingmachinebysamson.ServiceController;

@Controller
public class VendingMachineController {

    @RequestMapping("/vendingmachine")
    public String VendingMachine(Model m) throws Exception {
        // return "redirect:vendingmachine/account/info";
        return "vendingmachine";
    }

    @RequestMapping("/vendingmachine/account/info")
    public String accountinfo(Model m) throws Exception {
        return "accountinfo";
    }

    @RequestMapping("/vendingmachine/product/all")
    public String allproductinfo(Model m) throws Exception {
        return "productmain";
    }

    @RequestMapping("/vendingmachine/product")
    public String productinfo(Model m, @RequestParam("id") int id) throws Exception {
        return "productdetail";
    }
}
package cn.moonshotacademy.vendingmachinebysamson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.moonshotacademy.vendingmachinebysamson.service.ServiceController;
import cn.moonshotacademy.vendingmachinebysamson.service.UI;

@RestController
public class RequestController {
    @Autowired
    private UI ui;
    @Autowired
    private ServiceController servicecontroller;

    @RequestMapping("/uirequest")
    public String VendingMachine(@RequestParam("request") String request) throws Exception {
        ui.addRequest(request);
        servicecontroller.startOnce();
        return ui.getResult();
    }
}
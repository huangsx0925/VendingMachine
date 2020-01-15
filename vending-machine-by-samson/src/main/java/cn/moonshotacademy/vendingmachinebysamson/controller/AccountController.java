package cn.moonshotacademy.vendingmachinebysamson.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import cn.moonshotacademy.vendingmachinebysamson.Account;
import cn.moonshotacademy.vendingmachinebysamson.dao.AccountDAO;
import cn.moonshotacademy.vendingmachinebysamson.pojo.AccountCategory;

@RestController
// @RestController
public class AccountController {
    @Autowired
    AccountDAO accountDAO;

    @RequestMapping("/account")
    public String getAccountByName(@RequestParam("name") String id) throws Exception {
        // List<AccountCategory> accountList = accountDAO.findAll();
        // for (AccountCategory accountCategory : accountList) {
        // if(accountCategory.getId()==id){
        // Account account = new Account();
        // account.setId(id);
        // account.setLogin(false);
        // account.setPassword(accountCategory.getPassword());
        // account.setRemain(accountCategory.getRemain());
        // account.setSales(accountCategory.getSalestr());
        // }
        // }
        // return null;

        List<AccountCategory> accountList = accountDAO.findAll();
        for (AccountCategory accountCategory : accountList) {
            if (accountCategory.getId().equals(id)) {
                return accountCategory.getId() + "_" + accountCategory.getPassword() + "_" + accountCategory.getRemain();
            }
        }
        return "null";
    }
}
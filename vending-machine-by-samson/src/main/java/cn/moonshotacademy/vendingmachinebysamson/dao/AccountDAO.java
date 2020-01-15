package cn.moonshotacademy.vendingmachinebysamson.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.moonshotacademy.vendingmachinebysamson.pojo.AccountCategory;

@Repository
public interface AccountDAO extends JpaRepository<AccountCategory,Integer>{
    List<AccountCategory> findAll();
}
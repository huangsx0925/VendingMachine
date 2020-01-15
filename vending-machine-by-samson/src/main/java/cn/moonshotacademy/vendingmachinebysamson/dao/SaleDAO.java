package cn.moonshotacademy.vendingmachinebysamson.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.moonshotacademy.vendingmachinebysamson.pojo.SaleCategory;

@Repository
public interface SaleDAO extends JpaRepository<SaleCategory,Integer>{
    List<SaleCategory> findAll();
    // List<SaleCategory> findAllById(int id);
}
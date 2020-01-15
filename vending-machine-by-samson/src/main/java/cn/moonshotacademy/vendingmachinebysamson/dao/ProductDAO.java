package cn.moonshotacademy.vendingmachinebysamson.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.moonshotacademy.vendingmachinebysamson.pojo.ProductCategory;

@Repository
public interface ProductDAO extends JpaRepository<ProductCategory,Integer>{
    List<ProductCategory> findAll();
}
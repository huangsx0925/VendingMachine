package cn.moonshotacademy.vendingmachinebysamson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import cn.moonshotacademy.vendingmachinebysamson.service.ServiceController;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.moonshotacademy.vendingmachinebysamson.controller","cn.moonshotacademy.vendingmachinebysamson.service"})
@EntityScan(basePackages = "cn.moonshotacademy.vendingmachinebysamson.pojo")
@EnableJpaRepositories(basePackages = "cn.moonshotacademy.vendingmachinebysamson.dao")
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
	}

}

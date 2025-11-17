package com.cross.whale;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({"com.cross.whale.dao"})
@SpringBootApplication(scanBasePackages = {"com.cross.whale.config","com.cross.whale"})
public class PurchaseSalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseSalesApplication.class, args);
    }
}

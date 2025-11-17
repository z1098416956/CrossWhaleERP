package com.cross.whale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author TheSunshine
 * @date ${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}:${SECOND}
 */
@SpringBootApplication(scanBasePackages = {"com.cross.whale","com.cross.whale.config","com.cross.whale.minio.config"})
@EnableDiscoveryClient
@EnableFeignClients
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}

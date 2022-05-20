package com.wyd.wenyongdaCredit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableDiscoveryClient
@EnableOpenApi
@MapperScan(basePackages = {
        "com.wyd.wenyongdaCredit.mapper.*mapper"
})
public class CreditRunMain {
    public static void main(String[] args) {
        SpringApplication.run(CreditRunMain.class, args);
    }
}

package com.idhclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.idhclub.mapper")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.idhclub","org.n3r.idworker"}) //扫描定义类
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

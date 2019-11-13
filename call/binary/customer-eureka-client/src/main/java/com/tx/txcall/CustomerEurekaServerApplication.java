package com.tx.txcall;/**
 * Created by wyh in 2019/10/29 9:58
 **/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-29 09:58
 **/
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@ComponentScan("com.tx.txcall")
public class CustomerEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerEurekaServerApplication.class, args);
    }
}

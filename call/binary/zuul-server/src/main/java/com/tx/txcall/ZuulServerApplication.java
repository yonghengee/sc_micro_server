package com.tx.txcall;/**
 * Created by wyh in 2019/11/18 9:54
 **/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @program:
 * @description: 网关api
 * @author: forever-wang
 * @create: 2019-11-18 09:54
 **/
@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

}

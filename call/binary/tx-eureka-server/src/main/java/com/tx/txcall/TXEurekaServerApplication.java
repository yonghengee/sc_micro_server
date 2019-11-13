package com.tx.txcall; /**
 * Created by wyh in 2019/10/25 15:40
 **/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-25 15:40
 **/

@SpringBootApplication
@EnableEurekaServer
@ComponentScan({"com.tx.txcall"})
public class TXEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TXEurekaServerApplication.class, args);
    }
}

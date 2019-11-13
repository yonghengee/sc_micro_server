package com.tx.txcall.config;/**
 * Created by wyh in 2019/11/5 15:54
 **/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-11-05 15:54
 **/
@SpringBootApplication(scanBasePackages = "com.tx.txcall")
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}

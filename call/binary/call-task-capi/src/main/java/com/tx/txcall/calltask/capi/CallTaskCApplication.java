package com.tx.txcall.calltask.capi;/**
 * Created by wyh in 2019/10/31 10:03
 **/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-10-31 10:03
 **/
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.tx.txcall")
@EnableFeignClients(basePackages = {"com.tx.txcall.modules.calltask.api.remote"})
public class CallTaskCApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallTaskCApplication.class,args);
    }

}

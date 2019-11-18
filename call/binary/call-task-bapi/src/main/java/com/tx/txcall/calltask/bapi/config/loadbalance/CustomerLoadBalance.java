package com.tx.txcall.calltask.bapi.config.loadbalance;/**
 * Created by wyh in 2019/11/4 16:08
 **/

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-11-04 16:08
 **/
@Configuration
public class CustomerLoadBalance {

    @Bean
    public IRule callTaskRules() {

//        return new RandomRule();//随机
//        return new WeightedResponseTimeRule();//权重 根据平均响应时间计算所有服务的权重，响应时间越快服务权重越大被选中的概率越高。刚启动时，如果统计信息不足，则使用轮询策略，等信息足够，切换到 WeightedResponseTimeRule
        return new RoundRobinRule();//轮询
//        return new RetryRule();// 先按照轮询策略获取服务，如果获取失败则在指定时间内重试，获取可用服务
    }
}

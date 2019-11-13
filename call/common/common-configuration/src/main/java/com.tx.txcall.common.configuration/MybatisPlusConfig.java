package com.tx.txcall.common.configuration;/**
 * Created by wyh in 2019/6/5 19:21
 **/

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @description:
 * @author: forever-wang
 * @create: 2019-06-05 19:21
 **/

@Configuration
public class MybatisPlusConfig {
    /**
     *   mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        //默认最大条数
        page.setLimit(100000);
        return page;
    }

}

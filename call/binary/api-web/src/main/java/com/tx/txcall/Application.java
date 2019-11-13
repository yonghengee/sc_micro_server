package com.tx.txcall;
/**
 * Created by wyh in 2019/5/13 11:08
 **/

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: tx-call
 * @description:
 * @author: forever-wang
 * @create: 2019-05-13 11:08
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(value = {"com.tx"})
@MapperScan( {"com.tx.txcall.modules.**.service.mapper"})
@EnableSwagger2
@EnableAsync
@EnableCaching
@EnableScheduling
public class Application {
    public static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("SpringBoot开始加载");
        new SpringApplicationBuilder(Application.class).run(args);
        logger.info("SpringBoot加载完毕");

    }


    //    /**
    //     * it's for set http url auto change to https
    //     */
    //    @Bean
    //    public EmbeddedServletContainerFactory servletContainer(){
    //        TomcatEmbeddedServletContainerFactory tomcat=new TomcatEmbeddedServletContainerFactory(){
    //            @Override
    //            protected void postProcessContext(Context context) {
    //                SecurityConstraint securityConstraint=new SecurityConstraint();
    //                securityConstraint.setUserConstraint("CONFIDENTIAL");//confidential
    //                SecurityCollection collection=new SecurityCollection();
    //                collection.addPattern("/*");
    //                securityConstraint.addCollection(collection);
    //                context.addConstraint(securityConstraint);
    //            }
    //        };
    //        tomcat.addAdditionalTomcatConnectors(httpConnector());
    //        return tomcat;
    //    }
    //
    //    @Bean
    //    public Connector httpConnector(){
    //        Connector connector=new Connector("org.apache.coyote.http11.Http11NioProtocol");
    //        connector.setScheme("http");
    //        connector.setPort(9048);
    //        connector.setSecure(false);
    //        connector.setRedirectPort(8443);
    //        return connector;
    //    }
    //下面是2.0的配置，1.x请搜索对应的设置
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        tomcat.addAdditionalTomcatConnectors(createHTTPConnector());
//        return tomcat;
//    }
//
//    private Connector createHTTPConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        //同时启用http（8080）、https（9049）两个端口
//        connector.setScheme("http");
//        connector.setSecure(false);
//        connector.setPort(8080);
//        connector.setRedirectPort(9049);
//        return connector;
//    }


}

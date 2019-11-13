package com.tx.txcall.common.configuration;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zengxiaopeng
 * @title: Swagger2Config
 * @projectName master_cc
 * @description: TODO
 * @date 2019/4/1416:17
 */
@Configuration
public class Swagger2Config {
    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    // 定义分隔符
    private static final String splitor = ";";
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径"com.tx.txcall.modules.callout.task.web.controller," +
                .apis(basePackage(
                        "com.tx.txcall.modules"))
                .paths(PathSelectors.any())
                .build();
    }
    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("太信智能外呼系统")
                //创建人
                .contact("zengxiaopeng")
                //版本号
                .version("1.0")
                //描述
                .description("太信智能外呼系统API")
                .build();
    }
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage)     {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

}


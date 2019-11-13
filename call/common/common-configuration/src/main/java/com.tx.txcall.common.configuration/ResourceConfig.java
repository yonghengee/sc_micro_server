package com.tx.txcall.common.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class ResourceConfig implements WebMvcConfigurer {
	@Override
	/**
	 * 静态资源映射
	 */
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String os=System.getProperty("os.name");
		//判断是否windows系统
		if(os.toLowerCase().startsWith("win")){
			registry.addResourceHandler("/**.mp3").addResourceLocations("file:"+ApplicationConfig.callRecordPath);
		}else{
			registry.addResourceHandler("/**.mp3").addResourceLocations("file:"+ApplicationConfig.callRecordPath);
		}

		registry.addResourceHandler("/file/upload/**").addResourceLocations("file:"+ResourceValues.uploadPath);

        registry.addResourceHandler("/doc2html/**").addResourceLocations("file:"+OfficePathConfig.OFFICE_BASE_DIR);
        WebMvcConfigurer.super.addResourceHandlers(registry);

	}


    @Override
    public void addInterceptors(
        InterceptorRegistry registry) {

//	    registry.addInterceptor(new LicenseCheckInterceptor()).addPathPatterns("/**");

    }

    //设置tomcat超期时间
    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(20000);
        configurer.registerCallableInterceptors(timeoutInterceptor());
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutInterceptor() {
        return new TimeoutCallableProcessingInterceptor();
    }
}

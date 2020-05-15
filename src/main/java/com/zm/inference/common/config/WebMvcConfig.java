package com.zm.inference.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description TODO
 * @Author zm
 * @Date 2020/5/14 16:49
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

//    /**配置静态资源处理*/
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//    }
}

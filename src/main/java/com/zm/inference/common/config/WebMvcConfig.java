package com.zm.inference.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Description 注册拦截器
 * @Author zm
 * @Date 2020/5/14 16:49
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private SessionInterceptor sessionInterceptor;

    /**
     * 对除登录注册外的请求进行拦截
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/inference/**","/view/**")
                .excludePathPatterns("/user/register","/user/login","/view/goIndex");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("index");
    }
}

package com.itheima.config;

import com.itheima.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器，排除登录和注册接口
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login", "/user/register");
    }
}

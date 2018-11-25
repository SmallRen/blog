package com.renbaojia.blog.configuration;

import com.renbaojia.blog.configuration.interceptor.SpringMVCInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Created with IntelliJ IDEA.
 * @Author renbaojia
 * @Date 2018/11/25 15:06
 * @Description:
 */
@Configuration
public class SessionInterceptor extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SpringMVCInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/static/**");
        super.addInterceptors(registry);
    }

    /**
     * 修改springboot中默认的静态文件路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler请求路径
        //addResourceLocations 在项目中的资源路径
        //setCacheControl 设置静态资源缓存时间
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        super.addViewControllers(registry);
    }
}

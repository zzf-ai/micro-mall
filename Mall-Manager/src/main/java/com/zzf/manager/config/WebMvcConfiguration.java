package com.zzf.manager.config;

import com.zzf.manager.interceptor.LoginAuthInterceptor;
import com.zzf.manager.properties.UserAuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zzf
 * @date 2024-01-05
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginAuthInterceptor loginAuthInterceptor;

    @Autowired
    private UserAuthProperties userAuthProperties;

    //拦截器注册
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginAuthInterceptor)
                .excludePathPatterns(userAuthProperties.getNoAuthUrls())
                .addPathPatterns("/**");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //添加路径规则
                .allowCredentials(true)         //是否允许在跨域的情况下传递cookie
                .allowedOriginPatterns("*")     //允许请求来源的域规则
                .allowedHeaders("*")
                .allowedMethods("GET","POST","PUT","DELETE");
    }
}

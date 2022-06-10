package com.itranlin.basic.common.config;

import com.itranlin.basic.common.interceptor.TokenInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author itranlin
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final TokenInterceptor tokenInterceptor;

    public InterceptorConfig(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<String>() {{
            add("/api/account/sign-in");
            add("/v2/**");
            add("/swagger-resources/**");
            add("/error");
            add("/webjars/**");
        }};
        registry.addInterceptor(tokenInterceptor).excludePathPatterns(excludePath);
    }


}

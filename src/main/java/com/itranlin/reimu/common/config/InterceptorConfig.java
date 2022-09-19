package com.itranlin.reimu.common.config;

import com.itranlin.reimu.common.interceptor.AccessTokenInterceptor;

import com.itranlin.reimu.common.interceptor.RefreshTokenInterceptor;
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
    private final AccessTokenInterceptor accessTokenInterceptor;
    private final RefreshTokenInterceptor refreshTokenInterceptor;

    public InterceptorConfig(AccessTokenInterceptor accessTokenInterceptor, RefreshTokenInterceptor refreshTokenInterceptor) {
        this.accessTokenInterceptor = accessTokenInterceptor;
        this.refreshTokenInterceptor = refreshTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<String>() {{
            add("/api/account/sign-in");
            add("/api/account/refresh");
            add("/api/account/mp-sign-in");
            add("/v2/**");
            add("/swagger-resources/**");
            add("/error");
            add("/webjars/**");
        }};
        registry.addInterceptor(accessTokenInterceptor).excludePathPatterns(excludePath);
        registry.addInterceptor(refreshTokenInterceptor).addPathPatterns("/api/account/refresh");
    }

}

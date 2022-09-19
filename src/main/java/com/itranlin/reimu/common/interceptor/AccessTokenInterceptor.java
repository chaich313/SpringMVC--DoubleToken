package com.itranlin.reimu.common.interceptor;

import com.itranlin.reimu.common.bean.RequestResult;
import com.itranlin.reimu.common.bean.StatusEnum;
import com.itranlin.reimu.common.handler.BaseContextHandler;
import com.itranlin.reimu.common.util.JacksonUtil;
import com.itranlin.reimu.common.util.TokenUtil;

import com.auth0.jwt.JWT;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author itranlin
 */
@Component
public class AccessTokenInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler) throws Exception {
        // 如果请求是资源则放行
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        String token = request.getHeader("Authorization");
        if (token != null) {
            boolean result = TokenUtil.verify(token);
            if (result) {
                if (JWT.decode(token).getClaim("tokenType").asString().equals(TokenUtil.TOKEN_TYPE_ACCESS)){
                    BaseContextHandler.setUsername(JWT.decode(token).getClaim("username").asString());
                    BaseContextHandler.setId(JWT.decode(token).getClaim("id").asString());
                    BaseContextHandler.setRole(JWT.decode(token).getClaim("role").asString());
                    BaseContextHandler.setToken(JWT.decode(token).getToken());
                    return true;
                }
            }
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setLocale(Locale.CHINA);
        response.getWriter().write(JacksonUtil.toString(
                RequestResult.builder().status(StatusEnum.NOT_SING_IN.code).msg(StatusEnum.NOT_SING_IN.msg).build()));
        return false;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler,
            Exception ex) {
        BaseContextHandler.remove();
    }
}

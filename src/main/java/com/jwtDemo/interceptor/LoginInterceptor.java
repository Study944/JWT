package com.jwtDemo.interceptor;
import com.jwtDemo.util.JwtUtil;
import com.jwtDemo.util.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行");
        // 获取token
        String token = request.getHeader("token");
        // 解析token
        Long id = JwtUtil.parseJwtToken(token);
        // 将用户id存入ThreadLocal
        UserContext.setUser(id);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除ThreadLocal
        UserContext.removeUser();
    }
}

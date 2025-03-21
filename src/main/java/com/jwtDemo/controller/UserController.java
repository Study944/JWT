package com.jwtDemo.controller;

import com.jwtDemo.domain.User;
import com.jwtDemo.util.JwtUtil;
import com.jwtDemo.util.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @PutMapping("/login")
    public void login(@RequestBody User user)
    {
        if (user.getUsername().equals("admin") && user.getPassword().equals("123456")){
            Long TTL = 1000 * 60 * 60 * 24L;
            String token = JwtUtil.createJwtToken(user.getId(),  TTL);
            log.info("登录成功，token:{}", token);
        }
    }

    @RequestMapping("/info")
    public void info()
    {
        Long userId = UserContext.getUser();
        log.info("用户id:{}", userId);
    }
}

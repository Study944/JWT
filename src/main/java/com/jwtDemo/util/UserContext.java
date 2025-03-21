package com.jwtDemo.util;

public class UserContext{
    private static ThreadLocal<Long> userThreadLocal = new ThreadLocal<>();

    public static void setUser(Long userId){
        userThreadLocal.set(userId);
    }

    public static Long getUser(){
        return userThreadLocal.get();
    }

    public static void removeUser(){
        userThreadLocal.remove();
    }
}

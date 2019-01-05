package com.sai.core.utils;

import com.sai.core.pojo.UserAuthInfo;

import java.util.List;

public class UserAuthUtil {
    private static ThreadLocal<UserAuthInfo> userAuthInfoThreadLocal = new ThreadLocal<>();

    public static void remove(){
        userAuthInfoThreadLocal.remove();
    }

    public static void set(UserAuthInfo authInfo){
        if(authInfo!=null){
            userAuthInfoThreadLocal.set(authInfo);
        }
    }

    public static UserAuthInfo get(){
        return userAuthInfoThreadLocal.get();
    }

    public static List<String> getRoles(){
        UserAuthInfo userAuthInfo = get();
        if(userAuthInfo != null){
            return userAuthInfo.getRolesList();
        }
        return null;
    }
}

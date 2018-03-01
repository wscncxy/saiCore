package com.sai.core.pojo;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by ZhouXiang on 2017/10/23 0023 18:35.
 */
public class StatelessToken implements AuthenticationToken {
    private String principal;
    private String token;

    public StatelessToken(String principal,String token){
        this.principal=principal;
        this.token=token;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {  return principal;}
    @Override
    public Object getCredentials() {  return token;}
    @Override
    public String toString(){
        return "StatelessToken:"+ JSONObject.toJSONString(this);
    }
}

package com.sai.core.utils;

import com.sai.core.pojo.StatelessToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by ZhouXiang on 2017/10/19 0019 17:20.
 */

public class BEAuthorizeRealm extends AuthorizingRealm {



    public BEAuthorizeRealm() {
        setCachingEnabled(false);
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        StatelessToken statelessToken = (StatelessToken) authenticationToken;
//        if (!RequestUtils.checkCode(permissionService.check(statelessToken.getToken()))) {
//            return null;
//        }
        return new SimpleAuthenticationInfo(statelessToken.getPrincipal(), statelessToken.getCredentials(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String principal = principals.getPrimaryPrincipal().toString();
//        TokenVo loginToken = TokenUtils.decryptToken(principal);
//        String rolesStr = loginToken.getRoles();
//        if (Strings.isNotBlank(rolesStr)) {
//            String[] rolesArray = rolesStr.split(",");
//            for (String role : rolesArray) {
//                authorizationInfo.addRole(role);
//            }
//        }
        return authorizationInfo;
    }
}

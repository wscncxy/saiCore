package com.sai.core.filter;

import com.sai.core.constents.AuthContants;
import com.sai.core.pojo.StatelessToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

/**
 * Created by ZhouXiang on 2017/10/23 0023 17:00.
 */
public class StatelessAuthFilter extends AccessControlFilter {
    private static final Logger log = LoggerFactory.getLogger(StatelessAuthFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        if (StringUtils.startsWith(uri, "/login") ||
                StringUtils.startsWith(uri, "/auth")||
                StringUtils.startsWith(uri,"/smsTemplate/messageReportExcel")||
                StringUtils.startsWith(uri, "/routerEureka")||
                StringUtils.startsWith(uri, "/base/area/list")||
                StringUtils.startsWith(uri, "/base/area/option")) {
            return true;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        InputStream is = null;
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String token = httpRequest.getHeader("token");
            if (StringUtils.isBlank(token)) {
                token = httpRequest.getHeader(AuthContants.AUTH_FRONT_TOKEN_KEY);
            }
            StatelessToken statelessToken = new StatelessToken(token, token);
            ThreadContext.unbindSubject();
            Subject subject = getSubject(request, response);
            subject.login(statelessToken);
            ThreadContext.bind(subject);
        } catch (Exception e) {
//            BaseResponse baseResponse = new BaseResponse();
//            baseResponse.setCode(StatusContent.RESULT_FAIL_CODE_NOLOGIN);
//            baseResponse.setMsg("登录失效,请重新登陆");
//            response.setContentType("application/json; charset=utf-8");
//            response.getWriter().write(JSONObject.toJSONString(baseResponse));
            return false;
        }

        return true;
    }

}

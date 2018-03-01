package com.sai.core.utils;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * Created by ZhouXiang on 2017/10/24 0024 15:07.
 */
public class MyDefaultWebSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session
        context.setSessionCreationEnabled(false);
        Subject threadContextSubject = ThreadContext.getSubject();
        if (threadContextSubject != null && threadContextSubject.isAuthenticated()) {
            context.setAuthenticated(threadContextSubject.isAuthenticated());
            context.setPrincipals(threadContextSubject.getPrincipals());
            System.out.println("=-=-=-===="+threadContextSubject.getPrincipals().toString());
        }
//        else{
//            context.setAuthenticated(false);
//            context.setPrincipals(null);
//        }
        return super.createSubject(context);
    }
}

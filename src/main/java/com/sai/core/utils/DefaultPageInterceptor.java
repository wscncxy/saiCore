package com.sai.core.utils;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;


@Intercepts(@Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class DefaultPageInterceptor extends PageInterceptor {

    public DefaultPageInterceptor() {

    }

    public static DefaultPageInterceptor getInstance() {
        DefaultPageInterceptor defaultPageInterceptor = new DefaultPageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        defaultPageInterceptor.setProperties(properties);
        return defaultPageInterceptor;
    }
}

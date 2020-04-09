package com.sai.core.utils;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * LogBackThreadConverter
 *
 * @author douwenrui
 */
public class LogBackThreadConverter extends ClassicConverter {

    /**
     * 依据当前线程号获取UUID
     *
     * @param loggingEvent
     * @return
     */
    @Override
    public String convert(ILoggingEvent loggingEvent) {

        return ThreadLocalSessionStorage.getCurrentContext();
    }
}

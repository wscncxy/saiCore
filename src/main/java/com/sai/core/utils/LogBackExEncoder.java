package com.sai.core.utils;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.io.IOException;

/**
 * LogBackExEncoder
 *
 * @author douwenrui
 */
public class LogBackExEncoder extends PatternLayoutEncoder {

    static {
        PatternLayout.defaultConverterMap.put("T", LogBackThreadConverter.class.getName());
        PatternLayout.defaultConverterMap.put("thread", LogBackThreadConverter.class.getName());
    }

    /**
     * doEncode
     *
     * @param event
     * @throws IOException
     */
    public void doEncode(ILoggingEvent event) throws IOException {
        super.encode(event);
    }
}

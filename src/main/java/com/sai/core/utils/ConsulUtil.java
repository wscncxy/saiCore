package com.sai.core.utils;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.*;
import com.sai.core.constants.Constants;
import com.sai.core.pojo.ConsulKVDeleteOptions;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.Properties;

public class ConsulUtil {

    private Consul consul;
    private AgentClient agentClient;
    private KeyValueClient keyValueClient;
    private EventClient eventClient;
    private HealthClient healthClient;
    private CoordinateClient coordinateClient;
    private boolean isInit = false;

    private ConsulUtil() {
    }

    private static class InnerSingleton {
        private static final ConsulUtil INSTANCE = new ConsulUtil();
    }

    public static final ConsulUtil getInstance() {
        return InnerSingleton.INSTANCE;
    }

    public void init(Properties properties) {
        if (properties != null) {
            String host = properties.getProperty("consul.host");
            Integer port = Integer.parseInt(properties.getProperty("consul.port"));
            init(host, port);
            isInit = true;
        } else {
            isInit = false;
            throw new NullPointerException("ConsulUtil's properties is null");
        }
    }

    public void init(Environment environment) {
        if (environment != null) {
            String host = environment.getProperty("consul.host");
            Integer port = Integer.parseInt(environment.getProperty("consul.port"));
            init(host, port);
            isInit = true;
        } else {
            isInit = false;
            throw new NullPointerException("spring environment is null");
        }
    }

    public void init(String host, Integer port) {
        consul = Consul.builder().withHostAndPort(HostAndPort.fromParts(host, port)).build();
        agentClient = consul.agentClient();
        keyValueClient = consul.keyValueClient();
    }

    public boolean hasInit() {
        return isInit;
    }

    /**
     * keyValueClient
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        return keyValueClient.getValueAsString(key).get();
    }

    public List<String> getSubKeys(String key) {
        return keyValueClient.getKeys(key);
    }

    public boolean setValue(String key) {
        keyValueClient.putValue(key);
        return true;
    }

    public boolean setValue(String key, String value) {
        try {
            keyValueClient.putValue(key, value, Constants.DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteKey(String key) {
        keyValueClient.deleteKey(key);
        return true;
    }

    public boolean deleteKeyAllValue(String key) {
        keyValueClient.deleteKey(key, ConsulKVDeleteOptions.isRecurse(true));
        return true;
    }

}

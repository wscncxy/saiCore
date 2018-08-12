package com.sai.core.pojo;

import com.orbitz.consul.option.DeleteOptions;

import java.util.Optional;

public class ConsulKVDeleteOptions extends DeleteOptions {
    public Boolean isRecurse = false;

    @Override
    public Optional<Long> getCas() {
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> getRecurse() {
        return Optional.of(isRecurse);
    }

    @Override
    public Optional<String> getDatacenter() {
        return Optional.empty();
    }

    public ConsulKVDeleteOptions setRecurse(Boolean recurse) {
        isRecurse = recurse;
        return this;
    }

    public static ConsulKVDeleteOptions isRecurse(boolean recurse) {
        return new ConsulKVDeleteOptions().setRecurse(recurse);
    }
}

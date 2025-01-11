package com.example.demo.configs.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceContextRouting extends AbstractRoutingDataSource {
    private static final ThreadLocal<Boolean> CONTEXT = new ThreadLocal<>();

    public static void setReadOnly(boolean isReadOnly) {
        CONTEXT.set(isReadOnly);
    }

    public static void clearContext() {
        CONTEXT.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return CONTEXT.get();
    }
}

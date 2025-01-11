package com.example.demo.configs.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceRouting extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void usePrimaryContext() {
        CONTEXT.set("primary");
    }

    public static void useReplicaContext() {
        CONTEXT.set("replica");
    }

    public static void clearContext() {
        CONTEXT.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return CONTEXT.get();
    }
}

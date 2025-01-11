package com.example.demo.configs.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Optional;

public class DataSourceRouting extends AbstractRoutingDataSource {
    private static final ThreadLocal<DataSourceType> CONTEXT = new ThreadLocal<>();

    public static void setCurrentDataSource(DataSourceType dataSource) {
        CONTEXT.set(dataSource);
    }

    public static void clear() {
        CONTEXT.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return Optional.ofNullable(CONTEXT.get()).orElse(DataSourceType.READ_WRITE);
    }
}

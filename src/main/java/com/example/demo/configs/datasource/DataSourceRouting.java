package com.example.demo.configs.datasource;

import static com.example.demo.configs.datasource.DataSourceContextType.PRIMARY;
import static com.example.demo.configs.datasource.DataSourceContextType.REPLICA;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceRouting extends AbstractRoutingDataSource {
    private static final ThreadLocal<DataSourceContextType> CONTEXT = new ThreadLocal<>();

    public static void usePrimaryContext() {
        CONTEXT.set(PRIMARY);
    }

    public static void useReplicaContext() {
        CONTEXT.set(REPLICA);
    }

    public static void clearContext() {
        CONTEXT.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return CONTEXT.get();
    }
}

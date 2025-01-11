package com.example.demo.configs.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Optional;

public class DataSourceRouting extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

   public static void usePrimarySource() {
       CONTEXT.set("primary");
   }

    public static void useReplicaSource() {
        CONTEXT.set("replica");
    }

    public static void clear() {
        CONTEXT.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return Optional.ofNullable(CONTEXT.get()).orElse("primary");
    }
}

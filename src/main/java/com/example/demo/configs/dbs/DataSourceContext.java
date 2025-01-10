package com.example.demo.configs.dbs;

import java.util.Optional;

public class DataSourceContext {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static String getCurrentDataSource() {
        return Optional.ofNullable(CONTEXT.get()).orElse("primary");
    }

    public static void setCurrentDataSource(String dataSource) {
        CONTEXT.set(dataSource);
    }

    public static void clear() {
        CONTEXT.remove();
    }
}

package com.example.demo.configs.dbs;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DataSourceRouting extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContext.getCurrentDataSource();
    }
}

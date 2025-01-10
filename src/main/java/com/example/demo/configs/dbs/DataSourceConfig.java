package com.example.demo.configs.dbs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.primary.url}")
    private String primaryUrl;

    @Value("${spring.datasource.primary.username}")
    private String primaryUsername;

    @Value("${spring.datasource.primary.password}")
    private String primaryPassword;

    @Value("${spring.datasource.replica.url}")
    private String replicaUrl;

    @Value("${spring.datasource.replica.username}")
    private String replicaUsername;

    @Value("${spring.datasource.replica.password}")
    private String replicaPassword;

    private DataSource primaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(primaryUrl);
        dataSource.setUsername(primaryUsername);
        dataSource.setPassword(primaryPassword);
        return dataSource;
    }

    private DataSource replicaDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(replicaUrl);
        dataSource.setUsername(replicaUsername);
        dataSource.setPassword(replicaPassword);
        return dataSource;
    }

    @Bean
    public DataSourceRouting actualDataSource() {
        DataSourceRouting routingDataSource = new DataSourceRouting();

        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("primary", primaryDataSource());
        dataSources.put("replica", replicaDataSource());

        routingDataSource.setTargetDataSources(dataSources);
        routingDataSource.setDefaultTargetDataSource(primaryDataSource());

        return routingDataSource;
    }
}

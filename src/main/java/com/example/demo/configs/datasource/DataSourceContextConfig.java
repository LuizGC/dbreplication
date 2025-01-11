package com.example.demo.configs.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.Map;

@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceContextConfig {

    private Map<String, String> primary;
    private Map<String, String> replica;

    @Bean
    public DataSource dataSource() {
        DataSource primaryDataSource = createDatasource(primary, false);
        DataSource replicaDataSource = createDatasource(replica, true);
        LazyConnectionDataSourceProxy routingDataSource = new LazyConnectionDataSourceProxy();
        routingDataSource.setTargetDataSource(primaryDataSource);
        routingDataSource.setReadOnlyDataSource(replicaDataSource);
        return routingDataSource;
    }

    private HikariDataSource createDatasource(Map<String, String> datasourceProperties, boolean readOnly) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(getUrl(datasourceProperties));
        dataSource.setUsername(getUsername(datasourceProperties));
        dataSource.setPassword(getPassword(datasourceProperties));
        dataSource.setMaximumPoolSize(getMaximumPoolSize(datasourceProperties));
        dataSource.setReadOnly(readOnly);
        return dataSource;
    }

    private String getUrl(Map<String, String> properties) {
        return properties.get("url");
    }

    private String getUsername(Map<String, String> properties) {
        return properties.get("username");
    }

    private String getPassword(Map<String, String> properties) {
        return properties.get("password");
    }

    private int getMaximumPoolSize(Map<String, String> properties) {
        return Integer.parseInt(properties.getOrDefault("maximum-pool-size", "30"));
    }
}

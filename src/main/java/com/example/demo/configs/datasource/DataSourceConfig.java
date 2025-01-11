package com.example.demo.configs.datasource;

import static com.example.demo.configs.datasource.DataSourceContextType.PRIMARY;
import static com.example.demo.configs.datasource.DataSourceContextType.REPLICA;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

    private Map<String, String> primary;
    private Map<String, String> replica;

    @Bean
    public DataSourceRouting actualDataSource() {
        DataSourceRouting routingDataSource = new DataSourceRouting();
        DataSource primaryDataSource = createDatasource(primary);
        DataSource replicaDataSource = createDatasource(replica);
        routingDataSource.setTargetDataSources(Map.of(
                PRIMARY, primaryDataSource,
                REPLICA, replicaDataSource
        ));
        routingDataSource.setDefaultTargetDataSource(primaryDataSource);
        return routingDataSource;
    }

    private DataSource createDatasource(Map<String, String> datasourceProperties) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(getUrl(datasourceProperties));
        dataSource.setUsername(getUsername(datasourceProperties));
        dataSource.setPassword(getPassword(datasourceProperties));
        dataSource.setMaximumPoolSize(getMaximumPoolSize(datasourceProperties));
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

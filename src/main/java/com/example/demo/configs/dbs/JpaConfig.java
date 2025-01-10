package com.example.demo.configs.dbs;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor

public class JpaConfig {

    private final DataSourceRouting actualDataSource; // Inject the routing DataSource

    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(actualDataSource); // Use routing DataSource for JPA
        factoryBean.setPackagesToScan("com.example.demo"); // Adjust to your package for entities
        return factoryBean;
    }

    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}


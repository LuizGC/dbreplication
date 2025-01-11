package com.example.demo.configs.datasource;

import static com.example.demo.configs.datasource.DataSourceType.READ_ONLY;
import static com.example.demo.configs.datasource.DataSourceType.READ_WRITE;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(transactional) && execution(* *(..))")
    public void beforeTransactionalMethod(Transactional transactional) {
        if (transactional.readOnly()) {
            DataSourceRouting.setCurrentDataSource(READ_ONLY);
        } else {
            DataSourceRouting.setCurrentDataSource(READ_WRITE);
        }
    }

    @After("@annotation(transactional) && execution(* *(..))")
    public void afterTransactionalMethod(Transactional transactional) {
        DataSourceRouting.clear();
    }
}


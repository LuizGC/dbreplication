package com.example.demo.configs.datasource;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
@Order(0) // ensure it will be executed before DataSourceContextRouting
public class DataSourceContextAspect {

    @Before("@annotation(transactional) && execution(* *(..))")
    public void beforeTransactionalMethod(Transactional transactional) {
        DataSourceContextRouting.setReadOnly(transactional.readOnly());
    }

    @After("@annotation(transactional) && execution(* *(..))")
    public void afterTransactionalMethod(Transactional transactional) {
        DataSourceContextRouting.clearContext();
    }
}


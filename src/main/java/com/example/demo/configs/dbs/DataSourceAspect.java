package com.example.demo.configs.dbs;

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
            DataSourceContext.setCurrentDataSource("replica");
        } else {
            DataSourceContext.setCurrentDataSource("primary");
        }
    }

    @After("@annotation(transactional) && execution(* *(..))")
    public void afterTransactionalMethod(Transactional transactional) {
        DataSourceContext.clear();
    }
}


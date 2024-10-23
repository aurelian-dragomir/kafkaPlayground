package com.playground.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionManager;

@Configuration
public class JpaConfig {

    @Primary
    @Bean
    public TransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}

package com.amsidh.mvc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRetry
@Slf4j
public class RetryConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(3000l);

        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        Map<Class<? extends Throwable>, Boolean> includeExceptions = new HashMap<>();
        includeExceptions.put(CannotAcquireLockException.class, true);
        includeExceptions.put(ResourceAccessException.class, true);
        includeExceptions.put(IOException.class, true);
        includeExceptions.put(HttpServerErrorException.class, true);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(5, includeExceptions);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }

}

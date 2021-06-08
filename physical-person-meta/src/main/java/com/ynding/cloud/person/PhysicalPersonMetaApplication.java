package com.ynding.cloud.person;

import com.alibaba.cloud.seata.feign.SeataFeignClientAutoConfiguration;
import com.ynding.cloud.common.annotation.CustomSwaggerConfig;
import com.ynding.cloud.seata.annotation.CustomSeataConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.sleuth.instrument.web.client.feign.TraceFeignClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * @author Administrator
 */
@SpringBootApplication(exclude = {SeataFeignClientAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
//@EnableHystrix
@EnableMongoAuditing
@EnableTransactionManagement
@CustomSwaggerConfig
@CustomSeataConfig
//@EntityScan("com.ynding.cloud.person.entity")
//@EnableMongoRepositories(basePackages="com.ynding.cloud.person.data")
public class PhysicalPersonMetaApplication {

    /*@LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate template = new RestTemplate();
        *//*List interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }*//*
        return template;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(PhysicalPersonMetaApplication.class, args);
    }

}

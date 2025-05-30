package com.spy.spyojbackendquestionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.spy.spyojbackendquestionservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.spy")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.spy.spyojbackendserviceclient.service"})
public class SpyojBackendQuestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpyojBackendQuestionServiceApplication.class, args);
    }

}

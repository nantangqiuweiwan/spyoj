package com.spy.spyojbackendjudgeservice;

import com.spy.spyojbackendjudgeservice.rabbitmq.InitRabbitMq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.spy")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.spy.spyojbackendserviceclient.service"})
public class SpyojBackendJudgeServiceApplication {

    public static void main(String[] args) {

        InitRabbitMq.doInit();
        SpringApplication.run(SpyojBackendJudgeServiceApplication.class, args);
    }

}

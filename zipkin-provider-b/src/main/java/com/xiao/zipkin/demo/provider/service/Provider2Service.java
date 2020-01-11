package com.xiao.zipkin.demo.provider.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 11:48
 * @since JDK 1.8
 */
@SpringBootApplication
//@EnableEurekaClient
public class Provider2Service
{
    public static void main(String[] args)
    {
        SpringApplication.run(Provider2Service.class, args);
    }
}

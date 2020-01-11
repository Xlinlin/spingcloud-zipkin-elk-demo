package com.xiao.zipkin.demo.privoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 09:43
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Provider1Service
{
    public static void main(String[] args)
    {
        SpringApplication.run(Provider1Service.class, args);
    }
}

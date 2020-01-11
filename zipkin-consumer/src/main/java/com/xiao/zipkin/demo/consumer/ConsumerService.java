package com.xiao.zipkin.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 14:48
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ConsumerService
{
    public static void main(String[] args)
    {
        SpringApplication.run(ConsumerService.class, args);
    }
}

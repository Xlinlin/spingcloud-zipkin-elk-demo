package com.xiao.zipkin.demo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 09:35
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}

package com.xiao.zipkin.demo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 16:27
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(ZipkinServerApp.class, args);
    }
}

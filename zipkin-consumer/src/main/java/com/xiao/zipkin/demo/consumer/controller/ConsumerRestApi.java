package com.xiao.zipkin.demo.consumer.controller;

import com.xiao.zipkin.demo.consumer.feign.Provider1FeignService;
import com.xiao.zipkin.demo.consumer.feign.Provider2FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 14:49
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/consumer/api")
public class ConsumerRestApi
{
    @Autowired
    private Provider1FeignService provider1FeignService;
    @Autowired
    private Provider2FeignService provider2FeignService;

    @RequestMapping("/getConsumerName")
    public String getConsumerName(String name)
    {
        return "Consumer-" + name;
    }

    @RequestMapping("/getV1Name")
    public String getV1Name(String name)
    {
        return provider1FeignService.getV1Name(name);
    }

    @RequestMapping("/getV1V2Name")
    public String getV2V2Name(String name)
    {
        return provider1FeignService.getV2Name(name);
    }

    @RequestMapping("/getV2Name")
    public String getV2Name(String name)
    {
        return provider2FeignService.getV2Name(name);
    }
}

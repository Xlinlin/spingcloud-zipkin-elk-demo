package com.xiao.zipkin.demo.consumer.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 14:50
 * @since JDK 1.8
 */
@FeignClient(name = Provider1FeignService.PROVIDER1_NAME, path = "/provider/v1/api")
public interface Provider1FeignService
{
    String PROVIDER1_NAME = "zipkin-provider-a";

    @RequestMapping("/getV1Name")
    String getV1Name(@RequestParam("name") String name);

    @RequestMapping("/getV2Name")
    String getV2Name(@RequestParam("name") String name);
}

package com.xiao.zipkin.demo.privoder.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 14:42
 * @since JDK 1.8
 */
@FeignClient(name = Provider2FeignService.PROVIDER2_NAME, path = "/provider/v2/api")
public interface Provider2FeignService
{
    String PROVIDER2_NAME = "zipkin-provider-b";

    /**
     * [简要描述]:<br/>
     * [详细描述]:<br/>
     *
     * @param name :
     * @return java.lang.String
     * xiaolinlin  2020/1/10 - 14:40
     **/
    @RequestMapping("/getV2Name")
    String getV2Name(@RequestParam("name") String name);
}

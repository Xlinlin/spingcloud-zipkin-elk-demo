package com.xiao.zipkin.demo.privoder.controller;

import com.xiao.zipkin.demo.privoder.feign.Provider2FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 11:44
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/provider/v1/api")
@Slf4j
public class Provider1RestApi
{
    /**
     * 远程调用服务
     */
    @Autowired
    private Provider2FeignService provider2FeignService;

    @RequestMapping("/getV1Name")
    public String getName(String name)
    {
        log.info("provider1-" + name);
        return "provider1-" + name;
    }

    @RequestMapping("/getV2Name")
    public String getV2Name(String name)
    {
        log.error("provider1-provider2" + name);
        return "provider1-" + provider2FeignService.getV2Name(name);
    }
}

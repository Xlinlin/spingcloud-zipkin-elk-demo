package com.xiao.zipkin.demo.provider.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 14:40
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/provider/v2/api")
public class ProviderRestApi
{
    /**
     * [简要描述]:<br/>
     * [详细描述]:<br/>
     *
     * @param name :
     * @return java.lang.String
     * xiaolinlin  2020/1/10 - 14:40
     **/
    @RequestMapping("/getV2Name")
    public String getName(String name) throws Exception
    {
        //        throw new Exception("Zipkin-test-error!");
        return "provider2-" + name;
    }
}

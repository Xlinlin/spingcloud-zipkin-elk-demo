package com.xiao.zipkin.common.zipkin.custom;

import java.util.List;

/**
 * [简要描述]: 自定义span发送器
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/11 18:13
 * @since JDK 1.8
 */
public interface CustomSpanSender
{
    /**
     * [简要描述]:自定义span发送器<br/>
     * [详细描述]:<br/>
     *
     * @param spans :
     * @return boolean
     * xiaolinlin  2020/1/11 - 18:17
     **/
    boolean sendSpans(List<String> spans);
}

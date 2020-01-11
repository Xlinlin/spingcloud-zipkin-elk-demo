package com.xiao.zipkin.common.zipkin.custom.impl;

import com.xiao.zipkin.common.zipkin.custom.CustomSpanSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/11 18:18
 * @since JDK 1.8
 */
@Slf4j
//@Service("customSpanSenderSlf4jImpl")
public class CustomSpanSenderSlf4jImpl implements CustomSpanSender
{
    /**
     * [简要描述]:自定义span发送器<br/>
     * [详细描述]:<br/>
     *
     * @param spans :
     * @return boolean
     * xiaolinlin  2020/1/11 - 18:17
     **/
    @Override
    public boolean sendSpans(List<String> spans)
    {
        for (String span : spans)
        {
            log.info(span);
        }
        return true;
    }
}

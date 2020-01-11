package com.xiao.zipkin.common.logback;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import net.logstash.logback.decorate.JsonFactoryDecorator;

/**
 * [简要描述]: 禁用jackson对日志非ascii码进行escape编码
 * [详细描述]: 处理中文乱码的问题
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 15:16
 * @since JDK 1.8
 */
public class ConsumerLogDecorator implements JsonFactoryDecorator
{
    @Override
    public MappingJsonFactory decorate(MappingJsonFactory mappingJsonFactory)
    {
        // 禁用对非ascii码进行escape编码的特性
        mappingJsonFactory.disable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
        return mappingJsonFactory;

    }
}

package com.xiao.zipkin.common.zipkin;

import com.xiao.zipkin.common.zipkin.custom.CustomSpanSender;
import com.xiao.zipkin.common.zipkin.sender.CustomZipkinSpanReporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.SpanReporter;
import org.springframework.cloud.sleuth.autoconfig.TraceAutoConfiguration;
import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import org.springframework.cloud.sleuth.sampler.PercentageBasedSampler;
import org.springframework.cloud.sleuth.sampler.SamplerProperties;
import org.springframework.cloud.sleuth.zipkin.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/11 11:21
 * @since JDK 1.8
 */
@Configuration
@EnableConfigurationProperties({ ZipkinProperties.class, SamplerProperties.class })
@ConditionalOnProperty(value = "spring.zipkin.custom.enabled", matchIfMissing = true)
@AutoConfigureBefore(TraceAutoConfiguration.class)
@Slf4j
public class ZipkinCustomAutoConfiguration
{
    @Autowired(required = false)
    private CustomSpanSender customSpanSender;
    
    @Value("${spring.zipkin.custom.skip-pattern:/api/message.*|/api/server/getbytopic}")
    private String skipPattern;

    @Bean
    @ConditionalOnMissingBean
    public ZipkinSpanReporter slf4jReporter(SpanMetricReporter spanMetricReporter, ZipkinProperties zipkin)
    {
        log.info(">>>初始化zipKin-feign-slf4j-reporter");
        return new CustomZipkinSpanReporter(zipkin.getFlushInterval(), spanMetricReporter, customSpanSender,skipPattern);
    }

    @Bean
    @ConditionalOnMissingBean
    public ZipkinRestTemplateCustomizer zipkinRestTemplateCustomizer(ZipkinProperties zipkinProperties)
    {
        return new DefaultZipkinRestTemplateCustomizer(zipkinProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public Sampler defaultTraceSampler(SamplerProperties config)
    {
        return new PercentageBasedSampler(config);
    }

    @Bean
    public SpanReporter zipkinSpanListener(ZipkinSpanReporter reporter, EndpointLocator endpointLocator,
            Environment environment)
    {
        return new ZipkinSpanListener(reporter, endpointLocator, environment);
    }

    @Configuration
    @ConditionalOnMissingBean(EndpointLocator.class)
    @ConditionalOnProperty(value = "spring.zipkin.locator.discovery.enabled", havingValue = "false", matchIfMissing = true)
    protected static class DefaultEndpointLocatorConfiguration
    {

        @Autowired(required = false)
        private ServerProperties serverProperties;

        @Autowired
        private ZipkinProperties zipkinProperties;

        @Autowired(required = false)
        private InetUtils inetUtils;

        @Value("${spring.application.name:unknown}")
        private String appName;

        @Bean
        public EndpointLocator zipkinEndpointLocator()
        {
            return new ServerPropertiesEndpointLocator(this.serverProperties, this.appName, this.zipkinProperties, this.inetUtils);
        }

    }

    @Configuration
    @ConditionalOnClass(DiscoveryClient.class)
    @ConditionalOnMissingBean(EndpointLocator.class)
    @ConditionalOnProperty(value = "spring.zipkin.locator.discovery.enabled", havingValue = "true")
    protected static class DiscoveryClientEndpointLocatorConfiguration
    {

        @Autowired(required = false)
        private ServerProperties serverProperties;

        @Autowired
        private ZipkinProperties zipkinProperties;

        @Autowired(required = false)
        private InetUtils inetUtils;

        @Value("${spring.application.name:unknown}")
        private String appName;

        @Autowired(required = false)
        private DiscoveryClient client;

        @Bean
        public EndpointLocator zipkinEndpointLocator()
        {
            return new FallbackHavingEndpointLocator(discoveryClientEndpointLocator(), new ServerPropertiesEndpointLocator(this.serverProperties, this.appName, this.zipkinProperties, this.inetUtils));
        }

        private DiscoveryClientEndpointLocator discoveryClientEndpointLocator()
        {
            if (this.client != null)
            {
                return new DiscoveryClientEndpointLocator(this.client, this.zipkinProperties);
            }
            return null;
        }

    }
}

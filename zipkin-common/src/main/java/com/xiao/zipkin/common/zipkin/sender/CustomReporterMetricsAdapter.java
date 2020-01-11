package com.xiao.zipkin.common.zipkin.sender;

import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import zipkin.reporter.ReporterMetrics;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/11 11:26
 * @since JDK 1.8
 */
public final class CustomReporterMetricsAdapter implements ReporterMetrics
{
    private final SpanMetricReporter spanMetricReporter;

    public CustomReporterMetricsAdapter(SpanMetricReporter spanMetricReporter)
    {
        this.spanMetricReporter = spanMetricReporter;
    }

    @Override
    public void incrementMessages()
    {
    }

    @Override
    public void incrementMessagesDropped(Throwable throwable)
    {
    }

    @Override
    public void incrementSpans(int i)
    {
        this.spanMetricReporter.incrementAcceptedSpans(i);
    }

    @Override
    public void incrementSpanBytes(int i)
    {
    }

    @Override
    public void incrementMessageBytes(int i)
    {
    }

    @Override
    public void incrementSpansDropped(int i)
    {
        this.spanMetricReporter.incrementDroppedSpans(i);
    }

    @Override
    public void updateQueuedSpans(int i)
    {
    }

    @Override
    public void updateQueuedBytes(int i)
    {
    }
}

package com.xiao.zipkin.common.zipkin.sender;

import com.xiao.zipkin.common.zipkin.custom.CustomSpanSender;
import com.xiao.zipkin.common.zipkin.custom.impl.CustomSpanSenderSlf4jImpl;
import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Sender;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * [简要描述]: zipkin做日志打印
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 22:10
 * @since JDK 1.8
 */
public class CustomZipkinSpanReporter implements ZipkinSpanReporter, Flushable, Closeable
{
    private final AsyncReporter<Span> delegate;
    private final Sender sender;
    private final String HTTP_START = "http:";

    private final Pattern skipPattern;

    public CustomZipkinSpanReporter(int flushInterval, SpanMetricReporter spanMetricReporter,
            CustomSpanSender customSpanSender, String skipPattern)
    {
        CustomSpanSender spanSender = customSpanSender;
        if (null == customSpanSender)
        {
            spanSender = new CustomSpanSenderSlf4jImpl();
        }
        if (!StringUtils.isEmpty(skipPattern))
        {
            this.skipPattern = Pattern.compile(skipPattern);
        }
        else
        {
            this.skipPattern = null;
        }
        sender = new CustomSender(spanSender);
        delegate = AsyncReporter.builder(this.sender)
                // historical constraint. Note: AsyncReporter supports memory bounds
                .queuedMaxSpans(1000).messageTimeout(flushInterval, TimeUnit.SECONDS)
                .metrics(new CustomReporterMetricsAdapter(spanMetricReporter)).build();
    }

    /**
     * Closes this stream and releases any system resources associated
     * with it. If the stream is already closed then invoking this
     * method has no effect.
     *
     * <p> As noted in {@link AutoCloseable#close()}, cases where the
     * close may fail require careful attention. It is strongly advised
     * to relinquish the underlying resources and to internally
     * <em>mark</em> the {@code Closeable} as closed, prior to throwing
     * the {@code IOException}.
     *
     * @exception IOException if an I/O error occurs
     */
    @Override
    public void close() throws IOException
    {
        this.delegate.close();
        this.sender.close();
    }

    /**
     * Flushes this stream by writing any buffered output to the underlying
     * stream.
     *
     * @exception IOException If an I/O error occurs
     */
    @Override
    public void flush() throws IOException
    {
        delegate.flush();
    }

    /**
     * Receives completed spans from {@link ZipkinSpanListener} and submits them to a Zipkin
     * collector.
     *
     * @param span
     */
    @Override
    public void report(Span span)
    {
        // http:/api/message/heartbeat
        if (null != skipPattern)
        {
            String name = span.name;
            // 过滤某些特定的api请求
            String requestApi = name.substring(HTTP_START.length(), name.length());
            if (skipPattern.matcher(requestApi).matches())
            {
                return;
            }
        }
        this.delegate.report(span);
    }
}

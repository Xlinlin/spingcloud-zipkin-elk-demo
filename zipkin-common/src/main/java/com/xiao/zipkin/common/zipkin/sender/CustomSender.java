package com.xiao.zipkin.common.zipkin.sender;

import com.xiao.zipkin.common.zipkin.custom.CustomSpanSender;
import lombok.extern.slf4j.Slf4j;
import zipkin.reporter.Callback;
import zipkin.reporter.Encoding;
import zipkin.reporter.Sender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * [简要描述]:
 * [详细描述]:
 *
 * @author xiaolinlin
 * @version 1.0, 2020/1/10 22:17
 * @since JDK 1.8
 */
@Slf4j
public class CustomSender implements Sender
{
    /**
     * 自定义span发送
     */
    private final CustomSpanSender customSpanSender;

    public CustomSender(CustomSpanSender customSpanSender)
    {
        this.customSpanSender = customSpanSender;
    }

    /**
     * close is typically called from a different thread
     */
    transient boolean closeCalled;

    /**
     * Returns the encoding this sender requires spans to have.
     */
    @Override
    public Encoding encoding()
    {
        return Encoding.JSON;
    }

    /**
     * Maximum bytes sendable per message including overhead. This can be calculated using {@link
     * #messageSizeInBytes(List)}
     */
    @Override
    public int messageMaxBytes()
    {
        return 5 * 1024 * 1024;
    }

    /**
     * Before invoking {@link Sender#sendSpans(List, Callback)}, callers must consider message
     * overhead, which might be more than encoding overhead. This is used to not exceed {@link
     * Sender#messageMaxBytes()}.
     *
     * <p>Note this is not always {@link Encoding#listSizeInBytes(List)}, as some senders have
     * inefficient list encoding. For example, Scribe base64's then tags each span with a category.
     *
     * @param spans
     */
    @Override
    public int messageSizeInBytes(List<byte[]> spans)
    {
        return encoding().listSizeInBytes(spans);
    }

    /**
     * Sends a list of encoded spans to a transport such as http or Kafka.
     *
     * <p>Note: Eventhough there's a callback, there's no guarantee implementations won't block.
     * Accordingly, this method should not be called on the operation being measured's thread.
     *
     * @param encodedSpans list of encoded spans.
     * @param callback signals either completion or failure
     * @exception IllegalStateException if {@link #close() close} was called.
     */
    @Override
    public void sendSpans(List<byte[]> encodedSpans, Callback callback)
    {
        if (this.closeCalled)
        {
            throw new IllegalStateException("close");
        }
        List<String> spans = new ArrayList<>();
        for (byte[] bytes : encodedSpans)
        {
            spans.add(new String(bytes));
        }
        if (customSpanSender.sendSpans(spans))
        {
            callback.onComplete();
        }
    }

    /**
     * Answers the question: Are operations on this component likely to succeed?
     *
     * <p>Implementations should initialize the component if necessary. It should test a remote
     * connection, or consult a trusted source to derive the result. They should use least resources
     * possible to establish a meaningful result, and be safe to call many times, even concurrently.
     *
     * @see CheckResult#OK
     */
    @Override
    public CheckResult check()
    {
        return CheckResult.OK;
    }

    /**
     * Closes any network resources created implicitly by the component.
     *
     * <p>For example, if this created a connection, it would close it. If it was provided one, this
     * would close any sessions, but leave the connection open.
     */
    @Override
    public void close() throws IOException
    {
        this.closeCalled = true;
    }
}

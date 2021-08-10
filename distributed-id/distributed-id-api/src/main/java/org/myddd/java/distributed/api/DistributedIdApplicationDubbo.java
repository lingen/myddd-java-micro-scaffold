package org.myddd.java.distributed.api;

import java.util.concurrent.atomic.AtomicBoolean;

@javax.annotation.Generated(
        value = "by Dubbo generator",
        comments = "Source: DistributedApplication.proto")
public final class DistributedIdApplicationDubbo {
    private static final AtomicBoolean registered = new AtomicBoolean();

    public static boolean init() {
        if (registered.compareAndSet(false, true)) {
            org.apache.dubbo.common.serialize.protobuf.support.ProtobufUtils.marshaller(
                    com.google.protobuf.Empty.getDefaultInstance());
            org.apache.dubbo.common.serialize.protobuf.support.ProtobufUtils.marshaller(
                    com.google.protobuf.Int64Value.getDefaultInstance());
        }
        return true;
    }

    private DistributedIdApplicationDubbo() {
    }

}
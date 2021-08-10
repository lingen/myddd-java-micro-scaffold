package org.myddd.java.distributed.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

@javax.annotation.Generated(
        value = "by Dubbo generator",
        comments = "Source: DistributedApplication.proto")
public interface DistributedIdApplication {
    static final String JAVA_SERVICE_NAME = "org.myddd.java.distributed.api.DistributedIdApplication";
    static final String SERVICE_NAME = "org.myddd.distributed.distributed.api.DistributedIdApplication";
    //FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = DistributedIdApplicationDubbo.init();

    com.google.protobuf.Int64Value distributedId(com.google.protobuf.Empty request);

    CompletableFuture<com.google.protobuf.Int64Value> distributedIdAsync(com.google.protobuf.Empty request);


}
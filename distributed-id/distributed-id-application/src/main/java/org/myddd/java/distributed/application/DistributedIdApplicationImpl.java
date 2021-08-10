package org.myddd.java.distributed.application;

import com.google.protobuf.Empty;
import com.google.protobuf.Int64Value;
import io.grpc.stub.StreamObserver;
import org.myddd.java.distributed.api.DistributedIdApplication;

import java.util.concurrent.CompletableFuture;

public class DistributedIdApplicationImpl implements DistributedIdApplication {

    private SnowflakeDistributeId snowflakeDistributeId = new SnowflakeDistributeId(0,0);

    @Override
    public Int64Value distributedId(Empty request) {
        return Int64Value.of(snowflakeDistributeId.nextId());
    }

    @Override
    public CompletableFuture<Int64Value> distributedIdAsync(Empty request) {
        return CompletableFuture.completedFuture(distributedId(request));
    }
}

package org.myddd.java.distributed.application;

import org.myddd.java.distributed.api.IDGenerateApplication;

import java.util.UUID;

public class SnowflakeGenerateApplication implements IDGenerateApplication {

    private SnowflakeDistributeId snowflakeDistributeId = new SnowflakeDistributeId(0,0);
    @Override
    public Long nextId() {
        return snowflakeDistributeId.nextId();
    }
}

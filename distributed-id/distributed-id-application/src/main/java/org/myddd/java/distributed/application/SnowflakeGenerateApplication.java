package org.myddd.java.distributed.application;

import org.myddd.java.distributed.api.IDGenerateApplication;

import java.util.UUID;

public class SnowflakeGenerateApplication implements IDGenerateApplication {
    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }
}

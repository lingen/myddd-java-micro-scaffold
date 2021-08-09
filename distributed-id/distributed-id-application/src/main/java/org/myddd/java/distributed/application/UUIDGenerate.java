package org.myddd.java.distributed.application;

import org.myddd.java.distributed.api.IDGenerate;

import javax.inject.Named;
import java.util.UUID;

@Named
public class UUIDGenerate implements IDGenerate {
    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }
}

package org.myddd.java.distributed;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.myddd.java.distributed.api.IDGenerateApplication;
import org.myddd.java.distributed.application.SnowflakeGenerateApplication;

class TestIDGenerate {

    private IDGenerateApplication idGenerateApplication = new SnowflakeGenerateApplication();

    @Test
    void testGenerateId(){
        String nextId = idGenerateApplication.nextId();
        Assertions.assertNotNull(nextId);
    }
}

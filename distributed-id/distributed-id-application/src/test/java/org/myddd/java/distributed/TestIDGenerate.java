package org.myddd.java.distributed;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.myddd.java.distributed.api.IDGenerate;
import org.myddd.java.distributed.application.UUIDGenerate;

class TestIDGenerate {

    private IDGenerate idGenerate = new UUIDGenerate();

    @Test
    void testGenerateId(){
        String nextId = idGenerate.nextId();
        Assertions.assertNotNull(nextId);
    }
}

package org.myddd.java.distributed;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.myddd.domain.InstanceFactory;
import org.myddd.ioc.spring.SpringInstanceProvider;
import org.myddd.java.distributed.api.IDGenerateApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = Application.class)
public class TestApplication {

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    public void beforeClass(){
        InstanceFactory.setInstanceProvider(SpringInstanceProvider.createInstance(applicationContext));
    }

    @Test
    void testStart(){
        IDGenerateApplication idGenerateApplication = InstanceFactory.getInstance(IDGenerateApplication.class);
        Assertions.assertNotNull(idGenerateApplication);

        Long nextId = idGenerateApplication.nextId();
        Assertions.assertNotNull(nextId);
    }

}

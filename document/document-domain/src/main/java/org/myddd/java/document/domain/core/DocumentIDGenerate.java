package org.myddd.java.document.domain.core;

import org.myddd.domain.IDGenerate;
import org.myddd.domain.InstanceFactory;
import org.myddd.java.distributed.api.IDGenerateApplication;

import javax.inject.Named;
import java.util.Objects;

@Named
public class DocumentIDGenerate implements IDGenerate {


    private static IDGenerateApplication idGenerateApplication;

    private static IDGenerateApplication getIdGenerateApplication(){
        if (Objects.isNull(idGenerateApplication)){
            idGenerateApplication = InstanceFactory.getInstance(IDGenerateApplication.class);
        }
        return idGenerateApplication;
    }


    @Override
    public Long nextId() {
        return getIdGenerateApplication().nextId();
    }
}

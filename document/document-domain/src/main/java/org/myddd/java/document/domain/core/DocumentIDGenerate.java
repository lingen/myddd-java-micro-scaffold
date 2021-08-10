package org.myddd.java.document.domain.core;

import com.google.protobuf.Empty;
import org.myddd.domain.IDGenerate;
import org.myddd.domain.InstanceFactory;
import org.myddd.java.distributed.api.DistributedIdApplication;

import javax.inject.Named;
import java.util.Objects;

@Named
public class DocumentIDGenerate implements IDGenerate {


    private static DistributedIdApplication distributedIdApplication;

    private static DistributedIdApplication getIdGenerateApplication(){
        if (Objects.isNull(distributedIdApplication)){
            distributedIdApplication = InstanceFactory.getInstance(DistributedIdApplication.class);
        }
        return distributedIdApplication;
    }

    @Override
    public Long nextId() {
        return getIdGenerateApplication().distributedId(Empty.getDefaultInstance()).getValue();
    }
}

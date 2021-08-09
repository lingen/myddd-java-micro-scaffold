package org.myddd.java.document.domain;

import org.myddd.java.document.AbstractDocumentTest;
import org.myddd.java.document.domain.core.DocumentSpace;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

class TestDocumentSpace extends AbstractDocumentTest {

    @Test
    @Transactional
    void testCreateSharedDocument(){
        DocumentSpace sharedDocumentSpace = DocumentSpace.createSharedSpace();
        Assertions.assertTrue(sharedDocumentSpace.getId() > 0);
    }


}

package org.myddd.java.document.domain;

import org.myddd.java.document.AbstractDocumentTest;
import org.myddd.java.document.domain.core.Document;
import org.myddd.java.document.domain.core.DocumentHistory;
import org.myddd.java.document.domain.core.DocumentSpace;
import org.myddd.java.document.domain.core.DocumentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

class TestDocument extends AbstractDocumentTest {


    @Test
    @Transactional
    void testCreateDocument(){
        Document document = createDocument();
        Document createdDocument = document.createDocument();
        Assertions.assertTrue(createdDocument.getId() > 0);
    }

    @Transactional
    @Test
    void testDeleteDocument(){
        Document document = createDocument();
        Document createdDocument = document.createDocument();
        Assertions.assertTrue(createdDocument.getId() > 0);

        Assertions.assertDoesNotThrow(() -> {
            Document.deleteDocument(createdDocument.getId());
        });
    }

    @Test
    @Transactional
    void testCreateDir(){
        Document dir = Document.createRootDir(UUID.randomUUID().toString());
        Assertions.assertNotNull(dir);

        Document subDir = Document.createSubDir(dir.getId(),UUID.randomUUID().toString());
        Assertions.assertNotNull(subDir);

        Assertions.assertThrows(RuntimeException.class,() -> {
            Document.createSubDir(-1l,UUID.randomUUID().toString());
        });
    }

    @Test
    @Transactional
    void testCreateDocumentHistory(){
        Document document = createDocument().createDocument();
        DocumentHistory history = DocumentHistory.createHistory(document);
        Assertions.assertNotNull(history);
        Assertions.assertEquals(history.getName(),document.getName());
    }

    @Test
    @Transactional
    void testCreateHistory(){
        Document document = createDocument().createDocument();
        document.setMediaId(UUID.randomUUID().toString());
        document.setName(UUID.randomUUID().toString());

        Document updated = document.updateNewVersion();
        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(document.getId());
    }

    @Test
    @Transactional
    void testQueryHistories(){
        Document document = createDocument().createDocument();
        document.setMediaId(UUID.randomUUID().toString());
        document.setName(UUID.randomUUID().toString());

        Document updated = document.updateNewVersion();

        List<DocumentHistory> historyList = Document.queryHistories(updated.getId());
        Assertions.assertFalse(historyList.isEmpty());
    }

    private Document createDocument(){
        Document document = new Document();
        document.setSpace(DocumentSpace.createSharedSpace());
        document.setMd5(UUID.randomUUID().toString());
        document.setMediaId(UUID.randomUUID().toString());
        document.setName("NAME.mp3");
        document.setSuffix("mp3");
        document.setType(DocumentType.FILE);
        return document;
    }
}

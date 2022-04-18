package org.myddd.java.document.infra;

import org.myddd.java.document.domain.core.Document;
import org.myddd.java.document.domain.core.DocumentHistory;
import org.myddd.java.document.domain.core.DocumentRepository;
import org.myddd.java.document.domain.core.DocumentSpace;
import org.myddd.persistence.jpa.AbstractRepositoryJPA;

import javax.inject.Named;
import java.util.List;

@Named
public class DocumentRepositoryJPA extends AbstractRepositoryJPA implements DocumentRepository {
    @Override
    public DocumentSpace createDocumentSpace(DocumentSpace documentSpace) {
        documentSpace.setCreated(System.currentTimeMillis());
        return save(documentSpace);
    }

    @Override
    public Document createDocument(Document document) {
        document.setCreated(System.currentTimeMillis());
        return save(document);
    }

    @Override
    public Document queryDocumentByID(Long documentId) {
        return get(Document.class,documentId);
    }

    @Override
    public List<DocumentHistory> queryHistories(Long documentId) {
        return getEntityManager()
                .createQuery("from DocumentHistory where document.id =:documentId",DocumentHistory.class)
                .setParameter("documentId",documentId)
                .getResultList();
    }
}

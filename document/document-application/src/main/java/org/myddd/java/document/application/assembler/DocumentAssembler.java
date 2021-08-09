package org.myddd.java.document.application.assembler;

import org.myddd.java.document.api.dto.DocumentDTO;
import org.myddd.java.document.domain.core.Document;
import org.myddd.java.document.domain.core.DocumentType;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DocumentAssembler {

    @Inject
    private DocumentSpaceAssembler documentSpaceAssembler;

    public DocumentDTO toDTO(Document document){
        DocumentDTO dto = new DocumentDTO();
        dto.setId(document.getId());
        dto.setVersion(document.getVersion());
        dto.setCreated(document.getCreated());
        dto.setDelete(document.isDeleted());
        dto.setMd5(document.getMd5());
        dto.setMediaId(document.getMediaId());
        dto.setName(document.getName());
        dto.setParentId(document.getParentId());
        dto.setHistoryVersion(document.getHistoryVersion());
        dto.setSpace(documentSpaceAssembler.toDTO(document.getSpace()));
        dto.setSuffix(document.getSuffix());
        dto.setType(document.getType().toString());
        dto.setRemark(document.getRemark());
        return dto;
    }

    public Document toEntity(DocumentDTO dto){
        Document document = new Document();
        document.setId(dto.getId());
        document.setVersion(dto.getVersion());
        document.setCreated(dto.getCreated());
        document.setDeleted(dto.isDelete());
        document.setMd5(dto.getMd5());
        document.setMediaId(dto.getMediaId());
        document.setName(dto.getName());
        document.setParentId(dto.getParentId());
        document.setSpace(documentSpaceAssembler.toEntity(dto.getSpace()));
        document.setSuffix(dto.getSuffix());
        document.setHistoryVersion(dto.getHistoryVersion());
        document.setType(DocumentType.fromString(dto.getType()));
        document.setRemark(dto.getRemark());
        return document;
    }
}

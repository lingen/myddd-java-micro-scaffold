package org.myddd.java.document.application;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.myddd.java.document.api.DocumentApplication;
import org.myddd.java.document.api.dto.DocumentDTO;
import org.myddd.java.document.api.dto.DocumentHistoryDTO;
import org.myddd.java.document.application.assembler.DocumentAssembler;
import org.myddd.java.document.application.assembler.DocumentHistoryAssembler;
import org.myddd.java.document.domain.core.Document;
import org.myddd.java.document.domain.core.DocumentHistory;
import org.myddd.java.document.domain.core.DocumentType;

import org.myddd.querychannel.QueryChannelService;
import org.myddd.utils.Page;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentApplicationImpl implements DocumentApplication {

    @Inject
    private QueryChannelService queryChannelService;

    @Inject
    private DocumentAssembler documentAssembler;

    @Inject
    private DocumentHistoryAssembler documentHistoryAssembler;

    @Override
    @Transactional
    public DocumentDTO createRootDir(String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name),"文件名不能为空");
        Document createdDIRDocument = Document.createRootDir(name);
        return documentAssembler.toDTO(createdDIRDocument);
    }

    @Override
    @Transactional
    public DocumentDTO createSubDir(Long parentId, String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name),"文件名不能为空");
        Preconditions.checkNotNull(parentId,"指定的父文件夹为空");
        Document createdDIRDocument = Document.createSubDir(parentId,name);
        return documentAssembler.toDTO(createdDIRDocument);
    }

    @Override
    @Transactional
    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        Document created =  documentAssembler.toEntity(documentDTO).createDocument();
        return documentAssembler.toDTO(created);
    }

    @Override
    @Transactional
    public boolean createDocuments(List<DocumentDTO> documentDTOList) {
        for (DocumentDTO documentDTO : documentDTOList){
            createDocument(documentDTO);
        }
        return true;
    }

    @Transactional
    public DocumentDTO updateNewVersion(DocumentDTO documentDTO) {
        Document document = documentAssembler.toEntity(documentDTO);
        Document updatedDocument = document.updateNewVersion();
        return documentAssembler.toDTO(updatedDocument);
    }

    @Override
    @Transactional
    public void deleteDocument(Long documentId) {
        Preconditions.checkNotNull(documentId,"要删除的文档ID不能为空");
        Document.deleteDocument(documentId);
    }

    @Override
    public Page<DocumentDTO> pageSearchDocuments(String search, int page, int pageSize) {
        String sql = "from Document where name like ?1 and type = ?2 and deleted = false order by created desc";
        Page<Document> documentPage = queryChannelService
                .createJpqlQuery(sql,Document.class)
                .setParameters("%"+search+"%", DocumentType.FILE)
                .setPage(page,pageSize)
                .pagedList();
        return Page.builder(DocumentDTO.class)
                .data(documentPage.getData().stream().map(it -> documentAssembler.toDTO(it)).collect(Collectors.toList()))
                .pageSize(pageSize)
                .totalSize(documentPage.getResultCount())
                .start(documentPage.getStart());
    }

    @Override
    public Page<DocumentDTO> pageQueryRootDocuments(int page, int pageSize) {
        String sql = "from Document where deleted = false and parentId is null order by created desc";
        Page<Document> documentPage = queryChannelService
                .createJpqlQuery(sql,Document.class)
                .setPage(page,pageSize)
                .pagedList();
        return Page.builder(DocumentDTO.class)
                .data(documentPage.getData().stream().map(it -> documentAssembler.toDTO(it)).collect(Collectors.toList()))
                .pageSize(pageSize)
                .totalSize(documentPage.getResultCount())
                .start(documentPage.getStart());
    }

    @Override
    public Page<DocumentDTO> pageQuerySubDocuments(Long parentId, int page, int pageSize) {
        Preconditions.checkNotNull(parentId,"父ID不能为空");
        String sql = "from Document where deleted = false and parentId = ?1 order by created desc";
        Page<Document> documentPage = queryChannelService
                .createJpqlQuery(sql,Document.class)
                .setParameters(parentId)
                .setPage(page,pageSize)
                .pagedList();
        return Page.builder(DocumentDTO.class)
                .data(documentPage.getData().stream().map(it -> documentAssembler.toDTO(it)).collect(Collectors.toList()))
                .pageSize(pageSize)
                .totalSize(documentPage.getResultCount())
                .start(documentPage.getStart());
    }

    @Override
    public List<DocumentHistoryDTO> queryHistories(Long documentId) {
        List<DocumentHistory> documentHistories = Document.queryHistories(documentId);
        return documentHistories.stream().map(it -> documentHistoryAssembler.toDTO(it)).collect(Collectors.toList());
    }
}

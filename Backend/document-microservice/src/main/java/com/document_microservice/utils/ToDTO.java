package com.document_microservice.utils;

import com.document_microservice.DTOS.DocumentDTO;
import com.document_microservice.entities.Document;
import org.springframework.stereotype.Component;

@Component
public class ToDTO {
    public DocumentDTO convertToDocumentDTO(Document document) {
        return DocumentDTO.builder()
                .id(document.getId())
                .typeCreditDocument(document.getTypeCreditDocument())
                .documentName(document.getDocumentName())
                .documentType(document.getDocumentType())
                .build();
    }
}

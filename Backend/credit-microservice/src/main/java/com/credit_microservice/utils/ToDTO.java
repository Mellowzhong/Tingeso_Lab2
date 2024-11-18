package com.credit_microservice.utils;

import com.credit_microservice.DTOS.CreditDTO;
import com.credit_microservice.DTOS.DocumentDTO;
import com.credit_microservice.client.documentClient;
import com.credit_microservice.entities.Credit;
import com.credit_microservice.entities.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToDTO {
    private final documentClient documentClient;

    @Autowired
    public ToDTO(documentClient documentClient) {
        this.documentClient = documentClient;
    }

    public DocumentDTO convertToDocumentDTO(Document document) {
        return DocumentDTO.builder()
                .id(document.getId())
                .typeCreditDocument(document.getTypeCreditDocument())
                .documentName(document.getDocumentName())
                .documentType(document.getDocumentType())
                .build();
    }

    public CreditDTO convertToCreditDTO(Credit credit) {
        CreditDTO creditDTO = CreditDTO.builder()
                .id(credit.getId())
                .creditType(credit.getCreditType())
                .requestedAmount(credit.getRequestedAmount())
                .totalPriceHome(credit.getTotalPriceHome())
                .monthlyClientIncome(credit.getMonthlyClientIncome())
                .status(credit.getStatus())
                .applicationDate(credit.getApplicationDate())
                // .financialEvaluation(credit.getFinancialEvaluation())
                .userId(credit.getUserId())
                .build();

        List<DocumentDTO> documentDTOList = documentClient.getAllDocumentsByCreditId(credit.getId());

        creditDTO.setDocuments(documentDTOList);

        return creditDTO;
    }
}

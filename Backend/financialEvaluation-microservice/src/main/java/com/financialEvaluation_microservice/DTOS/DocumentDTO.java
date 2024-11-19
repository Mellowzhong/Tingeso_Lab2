package com.financialEvaluation_microservice.DTOS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDTO {
    private UUID id;
    private String typeCreditDocument;
    private String documentName;
    private String documentType;
}
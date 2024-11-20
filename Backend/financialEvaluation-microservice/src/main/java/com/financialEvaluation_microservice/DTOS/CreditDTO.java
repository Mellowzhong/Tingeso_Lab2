package com.financialEvaluation_microservice.DTOS;

import com.financialEvaluation_microservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditDTO {
    private UUID id;
    private String creditType;
    private Integer requestedAmount;
    private Integer totalPriceHome;
    private Integer monthlyClientIncome;
    private String status;
    private Date applicationDate;
    private User user;
    private UUID financialEvaluationId;
    private List<DocumentDTO> documents;
}


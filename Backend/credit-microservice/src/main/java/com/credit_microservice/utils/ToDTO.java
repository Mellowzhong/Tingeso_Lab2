package com.credit_microservice.utils;

import com.credit_microservice.DTOS.CreditDTO;
import com.credit_microservice.DTOS.DocumentDTO;
import com.credit_microservice.client.DocumentClient;
import com.credit_microservice.client.FinancialEvaluationClient;
import com.credit_microservice.client.UserClient;
import com.credit_microservice.entities.Credit;
import com.credit_microservice.entities.FinancialEvaluation;
import com.credit_microservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ToDTO {
    private final DocumentClient documentClient;
    private final UserClient userClient;
    private final FinancialEvaluationClient financialEvaluationClient;

    @Autowired
    public ToDTO(DocumentClient documentClient, UserClient userClient, FinancialEvaluationClient financialEvaluationClient) {
        this.documentClient = documentClient;
        this.userClient = userClient;
        this.financialEvaluationClient = financialEvaluationClient;
    }

    public CreditDTO convertToCreditDTO(Credit credit) {
        Optional<User> optionalUser = userClient.findUserById(credit.getUserId());

        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado para el crédito con ID: " + credit.getId());
        }

        CreditDTO.CreditDTOBuilder creditDTOBuilder = CreditDTO.builder()
                .id(credit.getId())
                .creditType(credit.getCreditType())
                .requestedAmount(credit.getRequestedAmount())
                .totalPriceHome(credit.getTotalPriceHome())
                .monthlyClientIncome(credit.getMonthlyClientIncome())
                .status(credit.getStatus())
                .applicationDate(credit.getApplicationDate())
                .user(optionalUser.get());

        Optional<FinancialEvaluation> optionalFinancialEvaluation = financialEvaluationClient.findFinancialEvaluationByCreditId(credit.getId());

        optionalFinancialEvaluation.ifPresent(creditDTOBuilder::financialEvaluation);

        List<DocumentDTO> documentDTOList = documentClient.getAllDocumentsByCreditId(credit.getId());

        CreditDTO creditDTO = creditDTOBuilder.build();

        creditDTO.setDocuments(documentDTOList);

        return creditDTO;
    }
}

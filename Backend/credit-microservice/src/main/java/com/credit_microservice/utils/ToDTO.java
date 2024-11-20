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
        System.out.println(credit.toString());
        Optional<User> optionalUser = userClient.findUserById(credit.getUserId());

        if (optionalUser.isPresent() && credit.getFinancialEvaluationId() != null) {
            Optional<FinancialEvaluation> optionalFinancialEvaluation = financialEvaluationClient.findFinancialEvaluationById(credit.getFinancialEvaluationId());
            if (optionalFinancialEvaluation.isPresent()) {
                CreditDTO creditDTO = CreditDTO.builder()
                        .id(credit.getId())
                        .creditType(credit.getCreditType())
                        .requestedAmount(credit.getRequestedAmount())
                        .totalPriceHome(credit.getTotalPriceHome())
                        .monthlyClientIncome(credit.getMonthlyClientIncome())
                        .status(credit.getStatus())
                        .applicationDate(credit.getApplicationDate())
                        .financialEvaluation(optionalFinancialEvaluation.get())
                        .user(optionalUser.get())
                        .build();

                List<DocumentDTO> documentDTOList = documentClient.getAllDocumentsByCreditId(credit.getId());

                creditDTO.setDocuments(documentDTOList);

                return creditDTO;
            }
        }else {
            CreditDTO creditDTO = CreditDTO.builder()
                    .id(credit.getId())
                    .creditType(credit.getCreditType())
                    .requestedAmount(credit.getRequestedAmount())
                    .totalPriceHome(credit.getTotalPriceHome())
                    .monthlyClientIncome(credit.getMonthlyClientIncome())
                    .status(credit.getStatus())
                    .applicationDate(credit.getApplicationDate())
                    .financialEvaluation(null)
                    .user(optionalUser.get())
                    .build();

            List<DocumentDTO> documentDTOList = documentClient.getAllDocumentsByCreditId(credit.getId());

            creditDTO.setDocuments(documentDTOList);

            return creditDTO;
        }

        return null;
    }
}

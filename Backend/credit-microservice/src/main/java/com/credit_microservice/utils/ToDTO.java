package com.credit_microservice.utils;

import com.credit_microservice.DTOS.CreditDTO;
import com.credit_microservice.DTOS.DocumentDTO;
import com.credit_microservice.entities.Credit;
import com.credit_microservice.entities.FinancialEvaluation;
import com.credit_microservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ToDTO {
//    private final DocumentClient documentClient;
//    private final UserClient userClient;
    final String userURL = "http://user-microservice/user";
    final String documentURL = "http://document-microservice/document";
    final String financialEvaluationURL = "http://financialEvaluation-microservice/financialEvaluation";
//    private final FinancialEvaluationClient financialEvaluationClient;

    @Autowired
    RestTemplate restTemplate;

    public CreditDTO convertToCreditDTO(Credit credit) {
        // Retrieve User object
        User user = restTemplate.getForObject(userURL + "/getById/" + credit.getUserId(), User.class);
        if (user == null) {
            throw new RuntimeException("User not found for ID: " + credit.getUserId());
        }

        // Build CreditDTO with basic credit information and user
        CreditDTO.CreditDTOBuilder creditDTOBuilder = CreditDTO.builder()
                .id(credit.getId())
                .creditType(credit.getCreditType())
                .requestedAmount(credit.getRequestedAmount())
                .totalPriceHome(credit.getTotalPriceHome())
                .monthlyClientIncome(credit.getMonthlyClientIncome())
                .status(credit.getStatus())
                .applicationDate(credit.getApplicationDate())
                .user(user);

        // Retrieve FinancialEvaluation object
        if (credit.getFinancialEvaluationId() != null) {
            FinancialEvaluation financialEvaluation = restTemplate.getForObject(
                    financialEvaluationURL + "/getById/" + credit.getFinancialEvaluationId(),
                    FinancialEvaluation.class
            );
            if (financialEvaluation != null) {
                creditDTOBuilder.financialEvaluation(financialEvaluation);
            }
        } else {
            System.out.println("Financial Evaluation ID is null for Credit ID: " + credit.getId());
        }

        // Retrieve list of DocumentDTOs
        List<DocumentDTO> documentDTOList = restTemplate.getForObject(documentURL + "/get/" + credit.getId(), List.class);

        CreditDTO creditDTO = creditDTOBuilder.build();

        if (documentDTOList != null) {
            creditDTO.setDocuments(documentDTOList);
        } else {
            creditDTO.setDocuments(Collections.emptyList());
        }

        return creditDTO;
    }
}

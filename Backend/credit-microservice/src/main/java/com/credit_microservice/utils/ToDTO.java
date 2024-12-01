package com.credit_microservice.utils;

import com.credit_microservice.DTOS.CreditDTO;
import com.credit_microservice.DTOS.DocumentDTO;
import com.credit_microservice.entities.Credit;
import com.credit_microservice.entities.FinancialEvaluation;
import com.credit_microservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class ToDTO {
//    private final DocumentClient documentClient;
//    private final UserClient userClient;
    final String userURL = "http://user-microservice/user";
    final String documentURL = "http://document-microservice/document";
    final String financialEvaluationURL = "http://financial-evaluation-microservice/financialEvaluation";
//    private final FinancialEvaluationClient financialEvaluationClient;

    @Autowired
    RestTemplate restTemplate;

    public CreditDTO convertToCreditDTO(Credit credit) {
//        Optional<User> optionalUser = userClient.findUserById(credit.getUserId());
        Optional<User> optionalUser = restTemplate.getForObject(userURL+ "/getById/" + credit.getUserId(), Optional.class);

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

//        Optional<FinancialEvaluation> optionalFinancialEvaluation = financialEvaluationClient.findFinancialEvaluationByCreditId(credit.getId());
        Optional<FinancialEvaluation> optionalFinancialEvaluation = restTemplate.getForObject(financialEvaluationURL + "/getById/" + credit.getFinancialEvaluationId(), Optional.class);

        optionalFinancialEvaluation.ifPresent(creditDTOBuilder::financialEvaluation);

//        List<DocumentDTO> documentDTOList = documentClient.getAllDocumentsByCreditId(credit.getId());
        List<DocumentDTO> documentDTOList = restTemplate.getForObject(documentURL + "/get/" + credit.getId(), List.class);

        CreditDTO creditDTO = creditDTOBuilder.build();

        creditDTO.setDocuments(documentDTOList);

        return creditDTO;
    }
}

package com.requestTracking.utils;

import com.requestTracking.DTOS.CreditDTO;
import com.requestTracking.entities.RequestTracking;
import org.springframework.stereotype.Component;

@Component
public class ToDTO {

    public RequestTracking convertToCreditDTO(CreditDTO creditDTO) {

        RequestTracking requestTracking = RequestTracking.builder()
                .financialEvaluationId(creditDTO.getFinancialEvaluation().getId())
                .evaluationResult(creditDTO.getFinancialEvaluation().getEvaluationResult())
                .status(creditDTO.getStatus())
                .creditType(creditDTO.getCreditType())
                .user(creditDTO.getUser())
                .build();

        return requestTracking;
    }
}

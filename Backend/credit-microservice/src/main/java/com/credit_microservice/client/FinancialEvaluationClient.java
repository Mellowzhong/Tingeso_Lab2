package com.credit_microservice.client;

import com.credit_microservice.entities.FinancialEvaluation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "financialEvaluation-microservice", url = "http://localhost:8080/financialEvaluation/")
public interface FinancialEvaluationClient {
    @GetMapping("/getById/{financialEvaluationId}")
    Optional<FinancialEvaluation> findFinancialEvaluationById(@PathVariable("financialEvaluationId") UUID financialEvaluationId);
}

package com.credit_microservice.client;

import com.credit_microservice.configurations.FeignClientConfig;
import com.credit_microservice.entities.FinancialEvaluation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "financialEvaluation-microservice")
public interface FinancialEvaluationClient {
    @GetMapping("/financialEvaluation/getById/{creditId}")
    Optional<FinancialEvaluation> findFinancialEvaluationByCreditId(@PathVariable("creditId") UUID creditId);
}

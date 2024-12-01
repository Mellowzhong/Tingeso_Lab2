package com.credit_microservice.client;

import com.credit_microservice.entities.FinancialEvaluation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(value = "financialEvaluation-microservice", path = "/financialEvaluation", configuration = {FeignClient.class})
public interface FinancialEvaluationClient {
    @GetMapping("/getById/{creditId}")
    Optional<FinancialEvaluation> findFinancialEvaluationByCreditId(@PathVariable("creditId") UUID creditId);
}

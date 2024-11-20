package com.financialEvaluation_microservice.client;

import com.financialEvaluation_microservice.DTOS.CreditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "credit-microservice", url = "http://localhost:8080/credit/")
public interface CreditClient {
    @GetMapping("/getCredit/{creditId}")
    Optional<CreditDTO> getCreditById(@PathVariable("creditId") UUID creditId);
}

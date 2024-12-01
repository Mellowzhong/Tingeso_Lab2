package com.document_microservice.client;

import com.document_microservice.DTOS.CreditDTO;
import com.document_microservice.configurations.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "credit-microservice")
public interface CreditClient {
    @GetMapping("/credit/getCredit/{creditId}")
    Optional<CreditDTO> getCreditById(@PathVariable("creditId") UUID creditId);
}

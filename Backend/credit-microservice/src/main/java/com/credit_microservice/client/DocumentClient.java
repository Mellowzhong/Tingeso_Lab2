package com.credit_microservice.client;

import com.credit_microservice.DTOS.DocumentDTO;
import com.credit_microservice.configurations.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "user-microservice")
public interface DocumentClient {
    @GetMapping("/document/get/{creditId}")
    List<DocumentDTO> getAllDocumentsByCreditId(@PathVariable("creditId") UUID creditId);
}

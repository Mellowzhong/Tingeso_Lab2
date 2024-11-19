package com.document_microservice.client;

import com.document_microservice.DTOS.CreditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "credit-microservice", url = "http://localhost:8080/user/")
public interface creditClient {
    @GetMapping("/get/{creditId}")
    Optional<CreditDTO> getCreditById(@PathVariable("creditId") UUID creditId);
}
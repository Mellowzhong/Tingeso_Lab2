package com.requestTracking.client;

import com.requestTracking.DTOS.CreditDTO;
import com.requestTracking.configurations.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "credit-microservice")
public interface CreditClient {
    @GetMapping("/credit/get/{user_id}")
    List<CreditDTO> getCreditByUserId(@PathVariable("user_id") UUID user_id);
}

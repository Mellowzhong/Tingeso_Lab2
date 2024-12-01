package com.credit_microservice.client;


import com.credit_microservice.configurations.FeignClientConfig;
import com.credit_microservice.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "user-microservice")
public interface UserClient {
    @GetMapping("/user/getById/{id}")
    Optional<User> findUserById(@PathVariable UUID id);
}

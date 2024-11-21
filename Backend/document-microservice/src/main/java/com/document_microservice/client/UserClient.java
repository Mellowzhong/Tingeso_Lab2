package com.document_microservice.client;

import com.document_microservice.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "user-microservice", url = "http://localhost:8080/user/")
public interface UserClient {
    @GetMapping("/getById/{id}")
    Optional<User> findUserById(@PathVariable UUID id);

}
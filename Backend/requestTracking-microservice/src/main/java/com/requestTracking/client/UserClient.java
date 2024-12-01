package com.requestTracking.client;

import com.requestTracking.entities.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(value = "user-microservice", path = "/user", configuration = {FeignClient.class})
public interface UserClient {
    @GetMapping("/getById/{id}")
    Optional<User> findUserById(@PathVariable UUID id);
}

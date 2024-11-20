package com.credit_microservice.repositories;

import com.credit_microservice.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {
    Optional<List<Credit>>  findAllByUserId(UUID userId);
}

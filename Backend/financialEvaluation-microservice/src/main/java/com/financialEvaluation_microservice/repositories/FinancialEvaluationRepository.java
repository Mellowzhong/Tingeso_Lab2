package com.financialEvaluation_microservice.repositories;

import com.financialEvaluation_microservice.entities.FinancialEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FinancialEvaluationRepository extends JpaRepository<FinancialEvaluation, UUID> {
    Optional<FinancialEvaluation> findByCreditId(UUID id);
}

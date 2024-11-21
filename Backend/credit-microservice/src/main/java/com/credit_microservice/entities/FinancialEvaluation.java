package com.credit_microservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialEvaluation {
    private UUID id;
    private Boolean feeToIncomeRatio;
    private Boolean creditHistory;
    private Boolean employmentHistory;
    private Boolean debtToIncomeRatio;
    private Boolean financeMaxAmount;
    private Boolean applicantAge;
    private Boolean savingCapacity;
    private Boolean evaluationResult;
    private UUID creditId;
}
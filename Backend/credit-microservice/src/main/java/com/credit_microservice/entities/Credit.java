package com.credit_microservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit")
@Builder
public class Credit {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String creditType;
    private Integer requestedAmount;
    private Integer totalPriceHome;
    private Integer monthlyClientIncome;
    private String status;
    private Date applicationDate;
    private UUID userId;
    private UUID financialEvaluationId;
}
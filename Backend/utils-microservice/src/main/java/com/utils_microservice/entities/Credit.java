package com.utils_microservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credit {
    private UUID id;

    private String creditType;

    private Integer requestedAmount;

    private Integer totalPriceHome;

    private Integer monthlyClientIncome;

    private String status;

    private Date applicationDate;

    private UUID userId;

    private List<Document> documents = new ArrayList<>();

//    private FinancialEvaluation financialEvaluation;
}

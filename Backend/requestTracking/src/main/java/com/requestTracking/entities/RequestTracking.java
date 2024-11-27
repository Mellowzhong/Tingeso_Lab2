package com.requestTracking.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestTracking {
    private UUID financialEvaluationId;
    private Boolean evaluationResult;
    private String status;
    private String creditType;
    private User user;
}

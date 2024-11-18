package com.utils_microservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalculateCreditSimulationDTO {
    private double creditAmount;
    private double simulatedInterestRate;
    private int numberOfPays;
    private int totalPriceHome;
    private String creditType;
}

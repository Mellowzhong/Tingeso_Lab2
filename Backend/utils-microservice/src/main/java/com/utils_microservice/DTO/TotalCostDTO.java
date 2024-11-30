package com.utils_microservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalCostDTO {
    private int creditAmount;
    private int numberOfPays;
    private int quote;
}

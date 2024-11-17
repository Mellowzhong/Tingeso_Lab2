package com.credit_microservice.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditResponse {
    private UUID id;

    private String creditType;

    private Integer requestedAmount;

    private Integer totalPriceHome;

    private Integer monthlyClientIncome;

    private String status;

    private Date applicationDate;

    private User user;
}

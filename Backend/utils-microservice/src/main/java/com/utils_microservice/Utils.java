package com.utils_microservice;

import com.utils_microservice.DTO.CalculateCreditSimulationDTO;
import com.utils_microservice.DTO.CalculateDebtToIncomeRatioDTO;
import com.utils_microservice.response.SimulationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/utils")
public class Utils {

    @PostMapping("/simulation")
    private SimulationResponse calculate (@RequestBody CalculateCreditSimulationDTO utilForm) {
        double amount = utilForm.getCreditAmount();
        double term = utilForm.getSimulatedInterestRate();
        double totalPriceHome = utilForm.getTotalPriceHome();
        double newTotalPriceHome = 0;
        String creditType = utilForm.getCreditType();

        if (creditType.equals("firstHome")) {
            newTotalPriceHome = (totalPriceHome * 0.8);
        }else if (creditType.equals("secondHome")) {
            newTotalPriceHome = totalPriceHome * 0.7;
        }else if (creditType.equals("commercialProperty")) {
            newTotalPriceHome = totalPriceHome * 0.6;
        }else if (creditType.equals("remodeling")) {
            newTotalPriceHome = totalPriceHome * 0.5;
        }

        int pay = utilForm.getNumberOfPays();

        double factor = Math.pow(1 + term, pay);
        double quote = (amount * term * factor) / (factor - 1);

        if (quote < totalPriceHome) {
            return SimulationResponse.builder()
                    .quote((int) Math.round(quote))
                    .message("Esta dentro del rango")
                    .totalPriceHome((int)Math.round(newTotalPriceHome))
                    .build();
        }else{
            return SimulationResponse.builder()
                    .quote((int) Math.round(quote))
                    .message("No esta dentro del rango")
                    .totalPriceHome((int)Math.round(newTotalPriceHome))
                    .build();
        }
    }

    @PostMapping("/debtToIncomeRatio")
    private SimulationResponse calculate2(@RequestBody CalculateDebtToIncomeRatioDTO utilForm) {
        double amount = utilForm.getCreditAmount();
        double term = utilForm.getSimulatedInterestRate();
        int monthlyClientIncome = utilForm.getMonthlyClientIncome();
        int pay = utilForm.getNumberOfPays();

        double factor = Math.pow(1 + term, pay);
        double quote = (amount * term * factor) / (factor - 1);
        int newQuote = (int) Math.round(quote * 1000);

        double debtToIncomeRatio = (double) newQuote / monthlyClientIncome;

        if (debtToIncomeRatio <= 35) {
            return SimulationResponse.builder()
                    .quote((int) Math.round(debtToIncomeRatio))
                    .message("Esta dentro del rango")
                    .build();
        } else {
            return SimulationResponse.builder()
                    .quote((int) Math.round(debtToIncomeRatio))
                    .message("No esta dentro del rango")
                    .build();
        }
    }
}

package com.financialEvaluation_microservice.controllers;

import com.financialEvaluation_microservice.entities.FinancialEvaluation;
import com.financialEvaluation_microservice.services.FinancialEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/financialEvaluation")
public class FinancialEvaluationController {
    private final FinancialEvaluationService financialEvaluationService;

    @Autowired
    public FinancialEvaluationController(FinancialEvaluationService financialEvaluationService) {
        this.financialEvaluationService = financialEvaluationService;
    }

    @PostMapping("/post/{creditId}")
    public ResponseEntity<FinancialEvaluation> postFinancialEvaluation(@PathVariable("creditId") UUID creditId, @RequestBody FinancialEvaluation financialEvaluation) {
        return financialEvaluationService.saveFinancialEvaluation(creditId, financialEvaluation);
    }

    @GetMapping("/getAll")
    public List<FinancialEvaluation> getAllFinancialEvaluation() {
        return financialEvaluationService.getAllFinancialEvaluations();
    }


    @PutMapping("/update/{creditId}/{financialEvaluationId}")
    public ResponseEntity<FinancialEvaluation> updateFinancialEvaluation(@PathVariable("creditId") UUID creditId, @PathVariable("financialEvaluationId") UUID financialEvaluationId,@RequestBody FinancialEvaluation financialEvaluation) {
        return financialEvaluationService.updateFinancialEvaluation(creditId, financialEvaluationId, financialEvaluation);
    }

//    Feign controllers
    @GetMapping("/getById/{financialEvaluationId}")
    public Optional<FinancialEvaluation> findFinancialEvaluationById(@PathVariable("financialEvaluationId") UUID financialEvaluationId) {
        return financialEvaluationService.getFinancialEvaluationById(financialEvaluationId);
    }
}


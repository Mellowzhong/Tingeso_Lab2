package com.financialEvaluation_microservice.services;

import com.financialEvaluation_microservice.DTOS.CreditDTO;
import com.financialEvaluation_microservice.client.CreditClient;
import com.financialEvaluation_microservice.entities.Credit;
import com.financialEvaluation_microservice.entities.FinancialEvaluation;
import com.financialEvaluation_microservice.repositories.FinancialEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FinancialEvaluationService {
    private final FinancialEvaluationRepository financialEvaluationRepository;
    private final CreditClient creditClient;

    @Autowired
    public FinancialEvaluationService(FinancialEvaluationRepository financialEvaluationRepository, CreditClient creditClient) {
        this.financialEvaluationRepository = financialEvaluationRepository;
        this.creditClient = creditClient;
    }

    public List<FinancialEvaluation> getAllFinancialEvaluations() {
        return financialEvaluationRepository.findAll();
    }

    public ResponseEntity<FinancialEvaluation> saveFinancialEvaluation(UUID creditID, FinancialEvaluation financialEvaluation) {
        Optional<CreditDTO> optionalCredit = creditClient.getCreditById(creditID);
        if (optionalCredit.isPresent()) {
            CreditDTO credit = optionalCredit.get();
            credit.setFinancialEvaluationId(financialEvaluation.getId());
            financialEvaluation.setCreditId(credit.getId());
            return new ResponseEntity<>(financialEvaluationRepository.save(financialEvaluation), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<FinancialEvaluation> updateFinancialEvaluation(UUID creditID, UUID financialEvaluationID,FinancialEvaluation financialEvaluation) {
        Optional<CreditDTO> optionalCredit = creditClient.getCreditById(creditID);
        if (optionalCredit.isPresent()) {
            Optional<FinancialEvaluation> getterFinancialEvaluation = financialEvaluationRepository.findById(financialEvaluationID);
            if (getterFinancialEvaluation.isPresent()) {
                financialEvaluation.setId(getterFinancialEvaluation.get().getId());
                financialEvaluation.setCreditId(optionalCredit.get().getId());
                return new ResponseEntity<>(financialEvaluationRepository.save(financialEvaluation), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    Feing services
    public Optional<FinancialEvaluation> findFinancialEvaluationByCreditId(UUID findByCreditId) {
        return financialEvaluationRepository.findByCreditId(findByCreditId);
    }
}

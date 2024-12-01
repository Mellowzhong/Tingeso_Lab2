package com.financialEvaluation_microservice.services;

import com.financialEvaluation_microservice.DTOS.CreditDTO;
import com.financialEvaluation_microservice.entities.FinancialEvaluation;
import com.financialEvaluation_microservice.repositories.FinancialEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FinancialEvaluationService {
    private final FinancialEvaluationRepository financialEvaluationRepository;
//    private final CreditClient creditClient;
    final String creditURL = "http://credit-microservice/credit";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public FinancialEvaluationService(FinancialEvaluationRepository financialEvaluationRepository) {
        this.financialEvaluationRepository = financialEvaluationRepository;
    }

    public List<FinancialEvaluation> getAllFinancialEvaluations() {
        return financialEvaluationRepository.findAll();
    }

    public ResponseEntity<FinancialEvaluation> saveFinancialEvaluation(UUID creditID, FinancialEvaluation financialEvaluation) {
//        Optional<CreditDTO> optionalCredit = creditClient.getCreditById(creditID);
        Optional<CreditDTO> optionalCredit = restTemplate.getForObject(creditURL + "/getCredit/" +creditID, Optional.class);

        if (optionalCredit.isPresent()) {
            CreditDTO credit = optionalCredit.get();
            credit.setFinancialEvaluationId(financialEvaluation.getId());
            financialEvaluation.setCreditId(credit.getId());
            return new ResponseEntity<>(financialEvaluationRepository.save(financialEvaluation), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<FinancialEvaluation> updateFinancialEvaluation(UUID creditID, UUID financialEvaluationID,FinancialEvaluation financialEvaluation) {
//        Optional<CreditDTO> optionalCredit = creditClient.getCreditById(creditID);
        Optional<CreditDTO> optionalCredit = restTemplate.getForObject(creditURL + "/getCredit/" +creditID, Optional.class);
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

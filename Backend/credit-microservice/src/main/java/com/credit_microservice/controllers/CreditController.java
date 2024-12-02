package com.credit_microservice.controllers;

import com.credit_microservice.DTOS.CreditDTO;
import com.credit_microservice.entities.Credit;
import com.credit_microservice.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/credit")
public class CreditController {
    private final CreditService creditService;

    @Autowired
    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @PostMapping("/add/{user_id}")
    public UUID addCredit(@RequestBody Credit credit, @PathVariable("user_id") UUID user_id) {
        return creditService.addCredit(credit, user_id);
    }

    @GetMapping("/get/{user_id}")
    public List<CreditDTO> getCreditByUserId(@PathVariable("user_id") UUID user_id) {
        return creditService.getAllCreditsByUserId(user_id);
    }

    @GetMapping("/getAll")
    public List<CreditDTO> getAllCredits() {
        return creditService.getAllCredits();
    }

//    Feign controllers
    @GetMapping("/getCredit/{creditId}")
    public CreditDTO getCreditById(@PathVariable("creditId") UUID creditId) {
        return creditService.getCreditById(creditId);
    }
}

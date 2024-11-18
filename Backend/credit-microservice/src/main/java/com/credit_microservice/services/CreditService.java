package com.credit_microservice.services;

import com.credit_microservice.DTOS.CreditDTO;
import com.credit_microservice.client.userClient;
import com.credit_microservice.DTOS.UserDTO;
import com.credit_microservice.entities.Credit;
import com.credit_microservice.repositories.CreditRepository;
import com.credit_microservice.utils.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreditService {
    private final CreditRepository creditRepository;
    private final userClient userClient;
    private final ToDTO toDTO; // Instancia de la clase de utilidades

    @Autowired
    public CreditService(CreditRepository creditRepository, userClient userClient, ToDTO toDTO) {
        this.creditRepository = creditRepository;
        this.userClient = userClient;
        this.toDTO = toDTO;
    }

    public UUID addCredit(Credit credit, UUID user_id) {
        Optional<UserDTO> optionalUser = userClient.findUserById(user_id);

        if (optionalUser.isPresent()) {
            UserDTO user = optionalUser.get();
            credit.setUserId(user.getId());
            creditRepository.save(credit);
            return credit.getId();
        }
        return null;
    }

    public List<CreditDTO> getAllCreditsByUserId(UUID user_id) {
        Optional<UserDTO> optionalUser = userClient.findUserById(user_id);
        if (optionalUser.isPresent()) {
            UserDTO user = optionalUser.get();
            return user.getCredits().stream()
                    .map(toDTO::convertToCreditDTO)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<CreditDTO> getAllCredits() {
        List<Credit> credits = creditRepository.findAll();
        return credits.stream()
                .map(toDTO::convertToCreditDTO)
                .collect(Collectors.toList());
    }

//    Feing services
    public Optional<CreditDTO> getCreditById(UUID credit_id) {
        Optional<Credit> credit = creditRepository.findById(credit_id);
        if (credit.isPresent()) {
            return Optional.of(toDTO.convertToCreditDTO(credit.get()));
        }
        return Optional.empty();
    }
}

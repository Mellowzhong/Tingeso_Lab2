package com.credit_microservice.services;

import com.credit_microservice.DTOS.CreditDTO;
import com.credit_microservice.entities.User;
import com.credit_microservice.entities.Credit;
import com.credit_microservice.repositories.CreditRepository;
import com.credit_microservice.utils.ToDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreditService {
    private final CreditRepository creditRepository;
    //    private final UserClient userClient;
    final String userURL = "http://user-microservice/user";
    private final ToDTO toDTO; // Instancia de la clase de utilidades

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public CreditService(CreditRepository creditRepository, ToDTO toDTO) {
        this.creditRepository = creditRepository;
        this.toDTO = toDTO;
    }

    public UUID addCredit(Credit credit, UUID user_id) {
//        Optional<User> optionalUser = userClient.findUserById(user_id);
        User user = restTemplate.getForObject(userURL+ "/getById/" + user_id, User.class);
        credit.setUserId(user.getId());
        creditRepository.save(credit);
        return credit.getId();
    }

    public UUID updateCredit(Credit credit, UUID credit_id) {
        Optional<Credit> creditOptional = creditRepository.findById(credit_id);
        if (creditOptional.isEmpty()) {
            throw new EntityNotFoundException("Credit not found");
        }

        creditRepository.save(credit);
        return credit.getId();
    }

    public List<CreditDTO> getAllCreditsByUserId(UUID user_id) {
        Optional<List<Credit>> creditList = creditRepository.findAllByUserId(user_id);

        if (creditList.isPresent()) {
            return creditList.get().stream()
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
    public CreditDTO getCreditById(UUID credit_id) {
        Optional<Credit> credit = creditRepository.findById(credit_id);

        return toDTO.convertToCreditDTO(credit.get());
    }
}

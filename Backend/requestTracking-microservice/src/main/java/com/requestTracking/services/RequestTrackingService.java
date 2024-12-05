package com.requestTracking.services;

import com.requestTracking.DTOS.CreditDTO;
import com.requestTracking.entities.RequestTracking;
import com.requestTracking.utils.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RequestTrackingService {

//    private final CreditClient creditClient;
//    private final UserClient userClient;
    final String creditURL = "http://credit-microservice/credit";
    private final ToDTO toDTO;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    public RequestTrackingService(ToDTO toDTO) {
        this.toDTO = toDTO;
    }

    public List<RequestTracking> getAllRequestTracking() {
        // Use ParameterizedTypeReference to specify the type of the list
        ResponseEntity<List<CreditDTO>> responseEntity = restTemplate.exchange(
                creditURL + "/getAll",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CreditDTO>>() {}
        );

        List<CreditDTO> creditDTOList = responseEntity.getBody();

        return creditDTOList.stream()
                .map(toDTO::convertToCreditDTO)
                .collect(Collectors.toList());
    }
}

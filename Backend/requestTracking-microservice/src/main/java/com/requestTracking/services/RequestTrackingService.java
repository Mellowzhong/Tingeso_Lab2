package com.requestTracking.services;

import com.requestTracking.DTOS.CreditDTO;
import com.requestTracking.entities.RequestTracking;
import com.requestTracking.utils.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
//        List<CreditDTO> creditDTOList = creditClient.getCreditByUserId(userId);
        List<CreditDTO> creditDTOList = restTemplate.getForObject(creditURL + "/getAll", List.class);

        return creditDTOList.stream()
                .map(toDTO::convertToCreditDTO)
                .collect(Collectors.toList());
    }
}

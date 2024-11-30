package com.requestTracking.services;

import com.requestTracking.DTOS.CreditDTO;
import com.requestTracking.client.CreditClient;
import com.requestTracking.client.UserClient;
import com.requestTracking.entities.RequestTracking;
import com.requestTracking.utils.ToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RequestTrackingService {

    private final CreditClient creditClient;
    private final UserClient userClient;
    private final ToDTO toDTO;

    @Autowired
    public RequestTrackingService(CreditClient creditClient, ToDTO toDTO, UserClient userClient) {
        this.creditClient = creditClient;
        this.userClient = userClient;
        this.toDTO = toDTO;
    }

    public List<RequestTracking> getAllRequestTracking(UUID userId) {
        List<CreditDTO> creditDTOList = creditClient.getCreditByUserId(userId);

        return creditDTOList.stream()
                .map(toDTO::convertToCreditDTO)
                .collect(Collectors.toList());
    }
}

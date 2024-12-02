package com.requestTracking.controllers;

import com.requestTracking.entities.RequestTracking;
import com.requestTracking.services.RequestTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/requestTracking")
public class RequestTrackingController {
    private final RequestTrackingService requestTrackingService;

    @Autowired
    public RequestTrackingController(RequestTrackingService requestTrackingService) {
        this.requestTrackingService = requestTrackingService;
    }

    @GetMapping("/getAll")
    public List<RequestTracking> getAllRequestTracking (){
        return requestTrackingService.getAllRequestTracking();
    }
}

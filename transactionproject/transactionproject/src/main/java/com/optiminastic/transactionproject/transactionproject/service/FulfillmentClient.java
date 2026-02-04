package com.optiminastic.transactionproject.transactionproject.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FulfillmentClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public String createFulfillment(Long clientId, Long orderId) {
        String url = "https://jsonplaceholder.typicode.com/posts";

        Map<String, Object> request = new HashMap<>();
        request.put("userId", clientId);
        request.put("title", orderId.toString());

        Map response = restTemplate.postForObject(url, request, Map.class);
        return response.get("id").toString();
    }
}

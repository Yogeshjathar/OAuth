package com.OAuth2.clientApp.GoogleOAuth2.Service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {

    private final RestTemplate restTemplate;

    public MessageService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String fetchMessageFromServiceA() {
        String serviceAUrl = "http://springboot-demo-service:8080/hello";  // Use Kubernetes service name
//        String serviceAUrl = "http://localhost:8081/hello";  // Use locally
        return restTemplate.getForObject(serviceAUrl, String.class);
    }
}

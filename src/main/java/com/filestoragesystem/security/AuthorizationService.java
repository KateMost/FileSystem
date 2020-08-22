package com.filestoragesystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthorizationService {

    private static final String API_KEY = "23567b218376f79d9415";

    @Autowired
    private RestTemplate restTemplate;

    public Optional<String> obtainApiToken() {
        Map<String, String> map = new HashMap<>();
        map.put("apiKey", API_KEY);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(map);
        ParameterizedTypeReference<Map<String, String>> responseType =
                new ParameterizedTypeReference<>() {
                };
        Map<String, String> response = restTemplate.exchange("http://interview.agileengine.com/auth", HttpMethod.POST, entity, responseType).getBody();
        return Optional.ofNullable(response)
                .map(body -> body.get("token"));
    }

}

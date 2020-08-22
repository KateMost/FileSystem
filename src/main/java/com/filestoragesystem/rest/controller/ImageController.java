package com.filestoragesystem.rest.controller;

import com.filestoragesystem.rest.model.ImageResponse;
import com.filestoragesystem.security.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ImageController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AuthorizationService authService;

    @GetMapping("/images")
    public ImageResponse getImages() {
        String authToken = authService.obtainApiToken().orElseThrow(IllegalStateException::new);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://interview.agileengine.com/images", HttpMethod.GET, requestEntity, ImageResponse.class).getBody();
    }

}

package com.example.asset_test.services.impl;

import com.example.communication.model.*;
import com.example.communication.services.JwtAutenticationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
public class JwtAuthenticationImpl implements JwtAutenticationService {

    public String ipDbRoot = "http://localhost:8081";

    @Value("${jwt.header}")
    private String tokenHeader;

    @Override
    public ResponseEntity<JwtAuthenticationResponse> createAuthenticationToken(JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) throws AuthenticationException, JsonProcessingException{
        final String url = String.format("%s/public/login", ipDbRoot);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", response.getHeader("Cookie"));
        headers.set("Postman-Token", response.getHeader("Postman-Token"));
        headers.set("Content-Type", response.getHeader("Content-Type"));
        headers.set("Content-Length", response.getHeader("Content-Length"));
        headers.set("Host", response.getHeader("Host"));
        HttpEntity<JwtAuthenticationRequest> requestEntity = new HttpEntity<JwtAuthenticationRequest>(authenticationRequest, headers);
       return restTemplate.exchange(url, HttpMethod.POST, requestEntity, JwtAuthenticationResponse.class);
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> refreshAndGetAuthenticationToken(HttpServletRequest request, HttpServletResponse response){
        final String url = String.format("%s/protected/refresh-token", ipDbRoot);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(tokenHeader, response.getHeader(tokenHeader));
        HttpEntity<HttpServletRequest> requestEntity = new HttpEntity<HttpServletRequest>(request, headers);
        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, JwtAuthenticationResponse.class);

    }
}

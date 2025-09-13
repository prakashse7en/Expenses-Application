package com.digital.digital_expenses_mcp.service;

import com.digital.digital_expenses_mcp.model.SecurityBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthMcpService {

    @Value("${auth.pazzword}")
    private String pazzword;

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.userName}")
    private String user;
    @Value("${auth.grantType}")
    private String grantType;

    @Value("${auth.tokenEndpoint}")
    private String tokenEndpoint;

    @Autowired
    RestTemplate restTemplate;

    public String getToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", grantType); // Or any other grant type
        body.add("client_id", clientId);
        body.add("username", user);
        body.add("password", pazzword);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<SecurityBO> response = restTemplate.postForEntity(tokenEndpoint, request, SecurityBO.class);

        return response.getBody().getAccess_token();
    }


}

package com.example.asset_test.service.impl;

import com.example.communication.model.Anagrafica;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AnagraficaServiceImpl {

    public JSONObject jo = new JSONObject();
    public String urlDB = "http://localhost:8081/graphql";

    public LinkedHashMap getAllanagrafica(HttpHeaders headers) throws JSONException {
        jo.put("query", "query {anagraficaAll { nome cognome } }");
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap newAnagrafica(HttpHeaders headers, Long id, String nome, String cognome) throws JSONException {
        String query = String.format("mutation {newAnagrafica(id: %s, nome: \"%s\", cognome: \"%s\") { nome cognome } }", id.toString(), nome, cognome);
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap anagraficaById(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("query {anagraficaById(id: %s) { nome cognome } }", id.toString());
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap updateAnagrafica(HttpHeaders headers, Long id, String nome, String cognome) throws JSONException {
        String query = String.format("mutation {updateAnagrafica(id: %s, nome: \"%s\", cognome: \"%s\") { nome cognome } }", id.toString(), nome, cognome);
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap deleteAnagrafica(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("mutation {deleteAnagrafica(id: %s)}", id.toString());
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }








}

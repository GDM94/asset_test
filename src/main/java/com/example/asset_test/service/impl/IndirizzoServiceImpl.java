package com.example.asset_test.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Service
public class IndirizzoServiceImpl {

    @Value("${ipDB}")
    private String urlDB;

    public JSONObject jo = new JSONObject();

    public LinkedHashMap<String, ?> indirizzoAll(HttpHeaders headers) throws JSONException {
        jo.put("query", "query {indirizzoAll { idaddress descrizione } }");
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> newIndirizzo(HttpHeaders headers, Long id, Long idana, String descrizione) throws JSONException {
        String query = String.format("mutation {newIndirizzo(id: %s, idana: %s, descrizione: \"%s\") { idaddress descrizione } }", id.toString(), idana.toString(), descrizione);
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> indirizzoById(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("query {indirizzoById(id: %s) { idaddress descrizione } }", id.toString());
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> updateIndirizzo(HttpHeaders headers, Long id, String descrizione) throws JSONException {
        String query = String.format("mutation {updateIndirizzo(id: %s, descrizione: \"%s\") {descrizione} }", id.toString(), descrizione);
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> deleteIndirizzo(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("mutation {deleteIndirizzo(id: %s)}", id.toString());
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

}

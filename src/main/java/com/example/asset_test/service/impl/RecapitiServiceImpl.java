package com.example.asset_test.service.impl;


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
public class RecapitiServiceImpl {

    public JSONObject jo = new JSONObject();
    public String urlDB = "http://localhost:8081/graphql";

    public LinkedHashMap<String, ?> getAllrecapiti(HttpHeaders headers) throws JSONException {
        jo.put("query", "query {recapitoAll { tipo_recapito numero_recapito anagrafica{nome, cognome} } }");
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> newRecapiti(HttpHeaders headers, Long id, Long idana, String tipo_recapito, String numero_recapito) throws JSONException {
        String query = String.format("mutation {newRecapiti(id: %s, idana: %s, tipo_recapito: \"%s\", numero_recapito: \"%s\") { tipo_recapito numero_recapito anagrafica{nome, cognome} } }", id.toString(), idana.toString(), tipo_recapito, numero_recapito);
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> recapitoById(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("query {recapitoById(id: %s) { tipo_recapito numero_recapito anagrafica{nome, cognome} } }", id.toString());
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> updateRecapiti(HttpHeaders headers, Long id, String tipo_recapito, String numero_recapito) throws JSONException {
        String query = String.format("mutation {updateRecapiti(id: %s, tipo_recapito: \"%s\", numero_recapito: \"%s\") { tipo_recapito numero_recapito anagrafica{nome, cognome} } }", id.toString(), tipo_recapito, numero_recapito);
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> deleteRecapiti(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("mutation {deleteRecapiti(idreca: %s)}", id.toString());
        jo.put("query", query);
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }
}

package com.example.asset_test.service.impl;

import com.example.communication.model.Anagrafica;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AnagraficaServiceImpl {

    @Value("${ipDB}")
    private String urlDB;

    public JSONObject jo = new JSONObject();
    RestTemplate restTemplate = new RestTemplate();

    public LinkedHashMap<String, ?> getAllanagrafica(HttpHeaders headers) throws JSONException {
        jo.put("query", "query {anagraficaAll { nome cognome } }");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        try {
            ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
            return response.getBody();
        } catch (HttpStatusCodeException ex){
            LinkedHashMap<String,String> responseError=new LinkedHashMap<String,String>();
            responseError.put("error", "Unauthorized");
            return responseError;
        }

    }

    public LinkedHashMap<String, ?> newAnagrafica(HttpHeaders headers, Long id, String nome, String cognome) throws JSONException {
        String query = String.format("mutation {newAnagrafica(id: %s, nome: \"%s\", cognome: \"%s\") { nome cognome } }", id.toString(), nome, cognome);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> anagraficaById(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("query {anagraficaById(id: %s) { nome cognome } }", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> updateAnagrafica(HttpHeaders headers, Long id, String nome, String cognome) throws JSONException {
        String query = String.format("mutation {updateAnagrafica(id: %s, nome: \"%s\", cognome: \"%s\") { nome cognome } }", id.toString(), nome, cognome);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }

    public LinkedHashMap<String, ?> deleteAnagrafica(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("mutation {deleteAnagrafica(id: %s)}", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        return response.getBody();
    }








}

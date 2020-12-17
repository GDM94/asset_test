package com.example.asset_test.service.impl;

import com.example.communication.bean.AnagraficaBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    public List<AnagraficaBean> getAllanagrafica(HttpHeaders headers) throws JSONException {
        jo.put("query", "query {anagraficaAll { idana nome cognome } }");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        try {
            List<AnagraficaBean> response = restTemplate.postForObject(urlDB, entity, List.class);
            return response;
        } catch (HttpStatusCodeException ex){
            return null;
        }

    }

    public AnagraficaBean newAnagrafica(HttpHeaders headers, Long id, String nome, String cognome) throws JSONException {
        String query = String.format("mutation {newAnagrafica(id: %s, nome: \"%s\", cognome: \"%s\") { idana nome cognome } }", id.toString(), nome, cognome);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        AnagraficaBean response = restTemplate.postForObject(urlDB, entity, AnagraficaBean.class);
        return response;
    }

    public AnagraficaBean anagraficaById(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("query {anagraficaById(id: %s) { idana nome cognome } }", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        AnagraficaBean anagraficaBean = restTemplate.postForObject(urlDB, entity, AnagraficaBean.class);
        return anagraficaBean;
    }

    public AnagraficaBean updateAnagrafica(HttpHeaders headers, Long id, String nome, String cognome) throws JSONException {
        String query = String.format("mutation {updateAnagrafica(id: %s, nome: \"%s\", cognome: \"%s\") { idana nome cognome } }", id.toString(), nome, cognome);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        AnagraficaBean response = restTemplate.postForObject(urlDB, entity, AnagraficaBean.class);
        return response;
    }

    public boolean deleteAnagrafica(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("mutation {deleteAnagrafica(id: %s)}", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        Boolean response = restTemplate.postForObject(urlDB, entity, Boolean.class);
        return response;
    }








}

package com.example.asset_test.service.impl;

import com.example.communication.model.Anagrafica;
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

    public List<Anagrafica> getAllanagrafica(HttpHeaders headers) throws JSONException {
        jo.put("query", "query {anagraficaAll { idana nome cognome } }");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        try {
            ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
            LinkedHashMap<String,  List<Anagrafica>> body = response.getBody();
            List<Anagrafica> result = body.get("anagraficaAll");
            return result;
        } catch (HttpStatusCodeException ex){
            return null;
        }

    }

    public Anagrafica newAnagrafica(HttpHeaders headers, Long id, String nome, String cognome) throws JSONException {
        String query = String.format("mutation {newAnagrafica(id: %s, nome: \"%s\", cognome: \"%s\") { nome cognome } }", id.toString(), nome, cognome);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        LinkedHashMap<String, Anagrafica> body = response.getBody();
        Anagrafica result = body.get("newAnagrafica");
        return result;
    }

    public Anagrafica anagraficaById(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("query {anagraficaById(id: %s) { idana nome cognome } }", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        LinkedHashMap<String, Anagrafica> body = response.getBody();
        ArrayList<Anagrafica> result = new ArrayList<Anagrafica>(body.values());
        Anagrafica anagrafica = result.get(0);
        return anagrafica;
    }

    public Anagrafica updateAnagrafica(HttpHeaders headers, Long id, String nome, String cognome) throws JSONException {
        String query = String.format("mutation {updateAnagrafica(id: %s, nome: \"%s\", cognome: \"%s\") { nome cognome } }", id.toString(), nome, cognome);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        LinkedHashMap<String, Anagrafica> body = response.getBody();
        Anagrafica result = body.get("updateAnagrafica");
        return result;
    }

    public boolean deleteAnagrafica(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("mutation {deleteAnagrafica(id: %s)}", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        ResponseEntity<LinkedHashMap> response = restTemplate.postForEntity(urlDB, entity, LinkedHashMap.class);
        LinkedHashMap<String, Boolean> body = response.getBody();
        boolean result = body.get("deleteAnagrafica");
        return result;
    }








}

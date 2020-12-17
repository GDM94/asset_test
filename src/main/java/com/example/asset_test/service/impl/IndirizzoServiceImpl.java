package com.example.asset_test.service.impl;

import com.example.communication.bean.IndirizziBean;
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

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class IndirizzoServiceImpl {

    @Value("${ipDB}")
    private String urlDB;

    public JSONObject jo = new JSONObject();
    RestTemplate restTemplate = new RestTemplate();

    public List<IndirizziBean> indirizzoAll(HttpHeaders headers) throws JSONException {
        jo.put("query", "query {indirizzoAll { idaddress idana descrizione } }");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        try {
            List<IndirizziBean> response = restTemplate.postForObject(urlDB, entity, List.class);
            return response;
        } catch (HttpStatusCodeException ex){
            return null;
        }

    }

    public IndirizziBean newIndirizzo(HttpHeaders headers, Long id, Long idana, String descrizione) throws JSONException {
        String query = String.format("mutation {newIndirizzo(id: %s, idana: %s, descrizione: \"%s\") { id descrizione } }", id.toString(), idana.toString(), descrizione);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        IndirizziBean response = restTemplate.postForObject(urlDB, entity, IndirizziBean.class);
        return response;
    }

    public IndirizziBean indirizzoById(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("query {anagraficaById(id: %s) { id idana descrizione } }", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        IndirizziBean anagraficaBean = restTemplate.postForObject(urlDB, entity, IndirizziBean.class);
        return anagraficaBean;
    }

    public IndirizziBean updateIndirizzo(HttpHeaders headers, Long id, String descrizione) throws JSONException {
        String query = String.format("mutation {newIndirizzo(id: %s, descrizione: \"%s\") { id descrizione } }", id.toString(), descrizione);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        IndirizziBean response = restTemplate.postForObject(urlDB, entity, IndirizziBean.class);
        return response;
    }

    public boolean deleteIndirizzo(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("mutation {deleteIndirizzo(id: %s)}", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        Boolean response = restTemplate.postForObject(urlDB, entity, Boolean.class);
        return response;
    }








}

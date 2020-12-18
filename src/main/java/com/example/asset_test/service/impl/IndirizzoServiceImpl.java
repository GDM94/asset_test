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
        jo.put("query", "query {indirizzoAll { idaddress descrizione anagrafica{idana nome cognome} } }");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        try {
            List<IndirizziBean> response = restTemplate.postForObject(urlDB, entity, List.class);
            return response;
        } catch (HttpStatusCodeException ex){
            return null;
        }

    }

    public IndirizziBean newIndirizzo(HttpHeaders headers, Long idana, String descrizione) throws JSONException {
        String query = String.format("mutation {newIndirizzo(idana: %s, descrizione: \"%s\") { idaddress descrizione anagrafica{idana nome cognome}} }", idana.toString(), descrizione);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        IndirizziBean response = restTemplate.postForObject(urlDB, entity, IndirizziBean.class);
        return response;
    }

    public IndirizziBean indirizzoById(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("query {indirizzoById(id: %s) { idaddress idana descrizione anagrafica{idana nome cognome} } }", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        IndirizziBean anagraficaBean = restTemplate.postForObject(urlDB, entity, IndirizziBean.class);
        return anagraficaBean;
    }

    public IndirizziBean updateIndirizzo(HttpHeaders headers, Long id, String descrizione) throws JSONException {
        String query = String.format("mutation {updateIndirizzo(id: %s, descrizione: \"%s\") { idaddress descrizione anagrafica{idana nome cognome}} }", id.toString(), descrizione);
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

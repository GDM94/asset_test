package com.example.asset_test.service.impl;


import com.example.communication.bean.RecapitiBean;
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
public class RecapitiServiceImpl {

    @Value("${ipDB}")
    private String urlDB;

    public JSONObject jo = new JSONObject();
    RestTemplate restTemplate = new RestTemplate();

    public List<RecapitiBean> recapitoAll(HttpHeaders headers) throws JSONException {
        jo.put("query", "query {recapitoAll { idreca numero_recapito tipo_recapito anagrafica{idana nome cognome} } }");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        try {
            List<RecapitiBean> response = restTemplate.postForObject(urlDB, entity, List.class);
            return response;
        } catch (HttpStatusCodeException ex) {
            return null;
        }

    }

    public RecapitiBean newRecapiti(HttpHeaders headers, Long idana, String tipo_recapito, String numero_recapito) throws JSONException {
        String query = String.format("mutation {newRecapiti( idana: %s, tipo_recapito: \"%s\", numero_recapito: \"%s\") { idreca tipo_recapito numero_recapito anagrafica{idana nome cognome} } }", idana.toString(), tipo_recapito, numero_recapito);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        RecapitiBean response = restTemplate.postForObject(urlDB, entity, RecapitiBean.class);
        return response;
    }

    public RecapitiBean recapitoById(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("query {recapitoById(id: %s) { idreca numero_recapito tipo_recapito anagrafica{idana nome cognome} } }", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        RecapitiBean anagraficaBean = restTemplate.postForObject(urlDB, entity, RecapitiBean.class);
        return anagraficaBean;
    }

    public RecapitiBean updateRecapiti(HttpHeaders headers, Long id, String tipo_recapito, String numero_recapito) throws JSONException {
        String query = String.format("mutation {updateRecapiti(id: %s, tipo_recapito: \"%s\", numero_recapito: \"%s\") { idreca numero_recapito tipo_recapito anagrafica{idana nome cognome} } }", id.toString(), tipo_recapito, numero_recapito);
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        RecapitiBean response = restTemplate.postForObject(urlDB, entity, RecapitiBean.class);
        return response;
    }

    public boolean deleteRecapiti(HttpHeaders headers, Long id) throws JSONException {
        String query = String.format("mutation {deleteRecapiti(id: %s)}", id.toString());
        jo.put("query", query);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jo.toString(), headers);
        Boolean response = restTemplate.postForObject(urlDB, entity, Boolean.class);
        return response;
    }

}

package com.example.asset_test.services.impl;

import com.example.communication.model.Anagrafica;
import com.example.communication.services.AnagraficaService;
import io.leangen.graphql.annotations.GraphQLMutation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnagraficaServiceImpl implements AnagraficaService {

    public String ipDbRoot = "http://localhost:8081";

    @Override
    public Anagrafica newAnagrafica(Long id, String nome, String cognome) {

        final String url = String.format("%s/createAnagrafica", ipDbRoot);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        //headers.set("X-TP-DeviceID", Global.deviceID);
        Anagrafica anagrafica = new Anagrafica();
        anagrafica.setIdana(id);
        anagrafica.setNome(nome);
        anagrafica.setCognome(cognome);
        //Map<String, String> param = new HashMap<String, String>();
        //param.put("id","10")
        HttpEntity<Anagrafica> requestEntity = new HttpEntity<Anagrafica>(anagrafica);
        HttpEntity<Anagrafica> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Anagrafica.class);
        return response.getBody();
    }

        @Override
        public boolean deleteAnagrafica(Long id){

            final String url = String.format("%s/deleteAnagrafica/{id}", ipDbRoot);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            Map<String, String> param = new HashMap<String, String>();
            param.put("id", id.toString());
            HttpEntity<Boolean> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Boolean.class, param);
            return response.getBody();



            }
}

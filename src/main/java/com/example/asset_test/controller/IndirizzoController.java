package com.example.asset_test.controller;

import com.example.asset_test.service.impl.IndirizzoServiceImpl;
import com.example.communication.model.Indirizzo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/indirizzo")
public class IndirizzoController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    IndirizzoServiceImpl indirizzoService;


    @GetMapping   // GET Method for reading operation
    public LinkedHashMap<String, ?> indirizzoAll(@RequestHeader HttpHeaders headers) throws JSONException {
        return indirizzoService.indirizzoAll(headers);
    }

    @GetMapping("/{id}")    // GET Method for reading operation
    public LinkedHashMap<String, ?> getIndirizzoById(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Long indId) throws JSONException {
        return indirizzoService.indirizzoById(headers, indId);
    }

    @PostMapping   // GET Method for reading operation
    public LinkedHashMap<String, ?> newIndirizzo(@RequestHeader HttpHeaders headers, @RequestBody Indirizzo ind) throws JSONException {
        return indirizzoService.newIndirizzo(headers, ind.getIdaddress(), ind.getIdana(), ind.getDescrizione());
    }

    @PutMapping("/{id}")
    public LinkedHashMap<String, ?> updateIndirizzo(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") long indId,  @RequestBody Indirizzo indDetails) throws JSONException {
        return indirizzoService.updateIndirizzo(headers, indId, indDetails.getDescrizione());
    }

    @DeleteMapping("/{id}")    // GET Method for reading operation
    public LinkedHashMap<String, ?> deleteIndirizzo(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Long indId) throws JSONException {
        return indirizzoService.deleteIndirizzo(headers, indId);
    }



}

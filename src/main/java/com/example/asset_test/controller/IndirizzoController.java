package com.example.asset_test.controller;

import com.example.asset_test.service.impl.IndirizzoServiceImpl;
import com.example.asset_test.service.impl.IndirizzoServiceImpl;
import com.example.communication.bean.IndirizziBean;
import com.example.communication.model.Indirizzo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/indirizzo")
public class IndirizzoController {

    @Value("${jwt.header}")
    private String tokenHeader;


    @Autowired
    IndirizzoServiceImpl indirizzoService;


    @GetMapping   // GET Method for reading operation
    public List<IndirizziBean> getAllindirizzo(@RequestHeader HttpHeaders headers) throws JSONException {
        List<IndirizziBean> indirizzoAll = indirizzoService.indirizzoAll(headers);
        return indirizzoAll;
    }

    @GetMapping("/{id}")    // GET Method for reading operation
    public IndirizziBean getIndirizzoById(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Long indId) throws JSONException {
        return indirizzoService.indirizzoById(headers, indId);
    }

    @PostMapping   // GET Method for reading operation
    public IndirizziBean newIndirizzo(@RequestHeader HttpHeaders headers, @RequestBody IndirizziBean ind) throws JSONException {
        return indirizzoService.newIndirizzo(headers, ind.getIdaddress(), ind.getIdana(), ind.getDescrizione());
    }

    @PutMapping("/{id}")
    public IndirizziBean updateIndirizzo(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") long indId,  @RequestBody IndirizziBean indDetails) throws JSONException {
        return indirizzoService.updateIndirizzo(headers, indId, indDetails.getDescrizione());
    }

    @DeleteMapping("/{id}")    // GET Method for reading operation
    public boolean deleteIndirizzo(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Long indId) throws JSONException {
        return indirizzoService.deleteIndirizzo(headers, indId);
    }



}

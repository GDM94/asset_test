package com.example.asset_test.controller;


import com.example.asset_test.service.impl.AnagraficaServiceImpl;
import com.example.asset_test.service.impl.RecapitiServiceImpl;
import com.example.communication.model.Anagrafica;
import com.example.communication.model.RecapitiTelefonici;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/recapiti")
public class RecapitiTelefoniciController {

    @Autowired
    RecapitiServiceImpl recapitiService;

    @GetMapping   // GET Method for reading operation
    public LinkedHashMap<String, ?> getAllrecapiti(@RequestHeader HttpHeaders headers) throws JSONException {
        return recapitiService.getAllrecapiti(headers);
    }

    @GetMapping("/{id}")    // GET Method for reading operation
    public LinkedHashMap<String, ?> getRecapitiById(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Long recaId) throws JSONException {
        return recapitiService.recapitoById(headers, recaId);
    }

    @PostMapping   // GET Method for reading operation
    public LinkedHashMap<String, ?> newRecapiti(@RequestHeader HttpHeaders headers, @RequestBody RecapitiTelefonici reca) throws JSONException {
        return recapitiService.newRecapiti(headers, reca.getIdreca(), reca.getIdana(), reca.getTipo_recapito(), reca.getNumero_recapito());
    }

    @PutMapping("/{id}")
    public LinkedHashMap<String, ?> updateRecapiti(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") long recaId,  @RequestBody RecapitiTelefonici reca) throws JSONException {
        return recapitiService.updateRecapiti(headers, recaId, reca.getTipo_recapito(), reca.getNumero_recapito());
    }

    @DeleteMapping("/{id}")    // GET Method for reading operation
    public LinkedHashMap<String, ?> deleteRecapiti(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Long recaId) throws JSONException {
        return recapitiService.deleteRecapiti(headers, recaId);
    }

}

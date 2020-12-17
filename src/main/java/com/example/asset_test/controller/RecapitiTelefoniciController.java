package com.example.asset_test.controller;


import com.example.asset_test.service.impl.RecapitiServiceImpl;
import com.example.asset_test.service.impl.RecapitiServiceImpl;
import com.example.communication.bean.RecapitiBean;
import com.example.communication.model.RecapitiTelefonici;
import com.example.communication.model.RecapitiTelefonici;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/recapiti")
public class RecapitiTelefoniciController {

    @Autowired
    RecapitiServiceImpl recapitoService;


    @GetMapping   // GET Method for reading operation
    public List<RecapitiBean> recapitoAll(@RequestHeader HttpHeaders headers) throws JSONException {
        List<RecapitiBean> recapitoAll = recapitoService.recapitoAll(headers);
        return recapitoAll;
    }

    @GetMapping("/{id}")    // GET Method for reading operation
    public RecapitiBean RecapitoById(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Long recaId) throws JSONException {
        return recapitoService.recapitoById(headers, recaId);
    }

    @PostMapping   // GET Method for reading operation
    public RecapitiBean newRecapiti(@RequestHeader HttpHeaders headers, @RequestBody RecapitiBean reca) throws JSONException {
        return recapitoService.newRecapiti(headers, reca.getIdreca(), reca.getIdana(), reca.getNumero_recapito(), reca.getTipo_recapito());
    }

    @PutMapping("/{id}")
    public RecapitiBean updateRecapiti(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") long recaId,  @RequestBody RecapitiBean recaDetails) throws JSONException {
        return recapitoService.updateRecapiti(headers, recaId, recaDetails.getNumero_recapito(), recaDetails.getTipo_recapito());
    }

    @DeleteMapping("/{id}")    // GET Method for reading operation
    public boolean deleteRecapiti(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Long recaId) throws JSONException {
        return recapitoService.deleteRecapiti(headers, recaId);
    }

}

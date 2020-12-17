package com.example.asset_test.controller;


import com.example.asset_test.service.impl.AnagraficaServiceImpl;
import com.example.communication.model.Anagrafica;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/anagrafica")
public class AnagraficaController {

    @Autowired
    AnagraficaServiceImpl anagraficaService;


    @GetMapping   // GET Method for reading operation
    public List<Anagrafica> getAllanagrafica(@RequestHeader HttpHeaders headers) throws JSONException {
        List<Anagrafica> anagraficaAll = anagraficaService.getAllanagrafica(headers);
        return anagraficaAll;
    }

    @GetMapping("/{id}")    // GET Method for reading operation
    public Anagrafica getAnagraficaById(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Long anaId) throws JSONException {
        return anagraficaService.anagraficaById(headers, anaId);
    }

    @PostMapping   // GET Method for reading operation
    public Anagrafica newAnagrafica(@RequestHeader HttpHeaders headers, @RequestBody Anagrafica ana) throws JSONException {
        return anagraficaService.newAnagrafica(headers, ana.getIdana(), ana.getNome(), ana.getCognome());
    }

    @PutMapping("/{id}")
    public Anagrafica updateAnagrafica(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") long anaId,  @RequestBody Anagrafica anaDetails) throws JSONException {
        return anagraficaService.updateAnagrafica(headers, anaId, anaDetails.getNome(), anaDetails.getCognome());
    }

    @DeleteMapping("/{id}")    // GET Method for reading operation
    public boolean deleteAnagrafica(@RequestHeader HttpHeaders headers, @PathVariable(value = "id") Long anaId) throws JSONException {
        return anagraficaService.deleteAnagrafica(headers, anaId);
    }



}

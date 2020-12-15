package com.example.asset_test.controller;


import com.example.asset_test.service.impl.AnagraficaServiceImpl;
import com.example.communication.model.Anagrafica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/anagrafica")
public class AnagraficaController {

    @Autowired
    AnagraficaServiceImpl anagraficaService;


    @GetMapping   // GET Method for reading operation
    public LinkedHashMap getAllanagrafica() throws JSONException {
        return anagraficaService.getAllanagrafica();
    }

    @GetMapping("/{id}")    // GET Method for reading operation
    public LinkedHashMap getAnagraficaById(@PathVariable(value = "id") Long anaId) throws JSONException {
        return anagraficaService.anagraficaById(anaId);
    }

    @PostMapping   // GET Method for reading operation
    public LinkedHashMap newAnagrafica(@RequestBody Anagrafica ana) throws JSONException {
        return anagraficaService.newAnagrafica(ana.getIdana(), ana.getNome(), ana.getCognome());
    }

    @PutMapping("/{id}")
    public LinkedHashMap updateAnagrafica(@PathVariable(value = "id") long anaId,  @RequestBody Anagrafica anaDetails) throws JSONException {
        return anagraficaService.updateAnagrafica(anaId, anaDetails.getNome(), anaDetails.getCognome());
    }

    @DeleteMapping("/{id}")    // GET Method for reading operation
    public LinkedHashMap deleteAnagrafica(@PathVariable(value = "id") Long anaId) throws JSONException {
        return anagraficaService.deleteAnagrafica(anaId);
    }



}

package com.example.asset_test.controller;


import com.example.asset_test.service.impl.AnagraficaServiceImpl;
import com.example.communication.model.Anagrafica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/anagrafica")
public class AnagraficaController {

    @Autowired
    AnagraficaServiceImpl anagraficaService;


    @GetMapping   // GET Method for reading operation
    public List<Anagrafica> getAllanagrafica() throws JSONException {
        return anagraficaService.getAllanagrafica();
    }

}

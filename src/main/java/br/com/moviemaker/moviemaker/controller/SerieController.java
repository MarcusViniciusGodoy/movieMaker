package br.com.moviemaker.moviemaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moviemaker.moviemaker.dto.SerieDTO;
import br.com.moviemaker.moviemaker.service.SerieService;

@RestController
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping("/series")
    public List<SerieDTO> obterSeries(){
        return service.obterTodasAsSeries();
    }
}

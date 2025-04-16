package br.com.moviemaker.moviemaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moviemaker.moviemaker.dto.SerieDTO;
import br.com.moviemaker.moviemaker.service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> obterSeries(){
        return service.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series(){
        return service.obterTop5Series();
    }

    @GetMapping("/lancamentos") 
    public List<SerieDTO> obterLancamentos(){
        return service.obterLancamentos();
    }

    @GetMapping("/{id}") 
    public SerieDTO obterPorId(@PathVariable Long id){
        return service.obterPorId(id);
    }
}

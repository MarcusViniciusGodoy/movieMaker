package br.com.moviemaker.moviemaker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moviemaker.moviemaker.dto.SerieDTO;
import br.com.moviemaker.moviemaker.model.Serie;
import br.com.moviemaker.moviemaker.repository.SerieRepository;

@RestController
public class SerieController {

    @Autowired
    private SerieRepository repository;

    @GetMapping("/series")
    public List<SerieDTO> obterSeries(){
        return repository.findAll()
        .stream().map(s -> new SerieDTO(s.getId(),
        s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(),
        s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
        .collect(Collectors.toList());
    }
}

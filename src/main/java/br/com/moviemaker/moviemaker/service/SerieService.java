package br.com.moviemaker.moviemaker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.moviemaker.moviemaker.dto.SerieDTO;
import br.com.moviemaker.moviemaker.model.Serie;
import br.com.moviemaker.moviemaker.repository.SerieRepository;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repository;

    public List<SerieDTO> obterTodasAsSeries(){
        return converteDados(repository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(repository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos() {
        return converteDados(repository.findTop5ByOrderByEpisodiosDataLancamentoDesc());
    }

    private List<SerieDTO> converteDados(List<Serie> series){
        return series.stream().map(s -> new SerieDTO(s.getId(),
            s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(),
            s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
            .collect(Collectors.toList());
    }
}

package br.com.moviemaker.moviemaker.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moviemaker.moviemaker.dto.EpisodioDTO;
import br.com.moviemaker.moviemaker.dto.SerieDTO;
import br.com.moviemaker.moviemaker.exception.ErroResponse;
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

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id){
        return service.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero){
        return service.obterTemporadasPorNumero(id, numero);
    }

    @GetMapping("/categoria/{nome}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nome){
        return service.obterSeriesPorCategoria(nome);
    }

    @GetMapping("/busca") 
    public List<SerieDTO> obterSeriesBuscadasSemTitulo(){
        return service.obterSeriesBuscadasSemTitulo();
    }

    @GetMapping("/busca/{titulo}")
    public ResponseEntity<?> obterSeriesBuscadasPorTitulo(@PathVariable String titulo) {
        try {
            List<SerieDTO> series = service.obterSeriesBuscadasPorTitulo(titulo);
    
            if (series.isEmpty()) {
                // Se a lista estiver vazia, retorna uma resposta de erro customizada
                ErroResponse erro = new ErroResponse("Nenhuma série encontrada", "Não foi possível encontrar séries para o título: " + titulo);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
            }
    
            // Se encontrar as séries, retorna elas normalmente
            return ResponseEntity.ok(series);
    
        } catch (Exception e) {
            // Caso algum erro inesperado aconteça, retorna uma resposta de erro com a mensagem
            ErroResponse erro = new ErroResponse("Erro interno do servidor", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
        }
    }
}

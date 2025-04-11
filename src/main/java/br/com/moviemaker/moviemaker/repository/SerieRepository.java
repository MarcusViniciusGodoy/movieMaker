package br.com.moviemaker.moviemaker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.moviemaker.moviemaker.model.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long>{

    Optional<Serie> findByTituloContainingIgnoreCasa(String nomeSerie);
}

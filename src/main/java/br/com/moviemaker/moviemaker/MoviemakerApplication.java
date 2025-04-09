package br.com.moviemaker.moviemaker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.moviemaker.moviemaker.principal.Principal;
import br.com.moviemaker.moviemaker.repository.SerieRepository;

@SpringBootApplication
public class MoviemakerApplication implements CommandLineRunner {

	@Autowired
    private SerieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MoviemakerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.exibeMenu();
	}
}

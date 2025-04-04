package br.com.moviemaker.moviemaker;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.moviemaker.moviemaker.principal.Principal;

@SpringBootApplication
public class MoviemakerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MoviemakerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
		
	}
}

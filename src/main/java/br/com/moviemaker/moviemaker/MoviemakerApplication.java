package br.com.moviemaker.moviemaker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.moviemaker.moviemaker.model.DadosSerie;
import br.com.moviemaker.moviemaker.service.ConsumoApi;
import br.com.moviemaker.moviemaker.service.ConverteDados;

@SpringBootApplication
public class MoviemakerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MoviemakerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
	}
}

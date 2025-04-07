package br.com.moviemaker.moviemaker.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.moviemaker.moviemaker.model.DadosEpisodios;
import br.com.moviemaker.moviemaker.model.DadosSerie;
import br.com.moviemaker.moviemaker.model.DadosTemporada;
import br.com.moviemaker.moviemaker.model.Episodios;
import br.com.moviemaker.moviemaker.service.ConsumoApi;
import br.com.moviemaker.moviemaker.service.ConverteDados;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para a busca: ");
        var nomeSerie = leitura.nextLine();
		var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i = 1; i<= dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);  
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
        
        /*List<DadosEpisodios> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());
        System.out.println("\nTop 10 episódios");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro filtro(N/A) " + e))
                .sorted(Comparator.comparing(DadosEpisodios::avaliacao).reversed())
                .peek(e -> System.out.println("Ordenação " + e))
                .limit(10)
                .peek(e -> System.out.println("Limite " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Mapeamento " + e))
                .forEach(System.out::println);*/

        List<Episodios> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                .map(d -> new Episodios(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        //Filtro busca por trecho do episódio
        /*System.out.println("Digite um trecho do titulo do episodio: ");
        var trechoTitulo = leitura.nextLine();
        Optional<Episodios> episodioBuscado = episodios.stream()
                 .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                 .findFirst();
        if (episodioBuscado.isPresent()){
            System.out.println("Episódio encontrado.");
            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
        } else {
            System.out.println("Episódio não encontrado.");
        }*/

        /*System.out.println("A partir de qual ano você deseja ver os episódios?");
        var ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        episodios.stream()
        .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
        .forEach(e -> System.out.println(
            "Temporada: " + e.getTemporada() +
            " Episódio: " + e.getTitulo() +
            " Data lançamento: " + e.getDataLancamento().format(formatador)
        ));*/

        //Dados por temporada
        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
            .filter(e -> e.getAvaliacao() > 0.0)
            .collect(Collectors.groupingBy(Episodios::getTemporada, 
            Collectors.averagingDouble(Episodios::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);
    }
}

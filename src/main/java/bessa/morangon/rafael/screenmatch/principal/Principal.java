package bessa.morangon.rafael.screenmatch.principal;

import bessa.morangon.rafael.screenmatch.model.*;
import bessa.morangon.rafael.screenmatch.repository.SerieRepository;
import bessa.morangon.rafael.screenmatch.service.ConsumoApi;
import bessa.morangon.rafael.screenmatch.service.ConverteDados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "http://www.omdbapi.com/";
    private final String API_KEY = "c87f1d3d";
    private List<DadosSerie> dadosSeries = new ArrayList<>();
    private SerieRepository repositorio;
    private List<Serie> series = new ArrayList<>();

    public Principal(SerieRepository repositorio) {

        this.repositorio = repositorio;
    }

    public void buscarTodasSeriesDaApiEArmazenarNoBanco() throws IOException {

        int page = 1;
        boolean maisResultados = true;

        while (maisResultados) {

            String json = consumo.obterDados(ENDERECO + "?s=series&page=" + page + "&apikey=" + API_KEY);



            for (Serie s : series) {
                repositorio.save(s);
            }

            maisResultados = series.size() == 200;
            page++;
        }
    }


}
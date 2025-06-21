package com.literatura.desafio.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        @JsonAlias("count") int contador,
        @JsonAlias("results") List<DatosLibro> resultados
//    @JsonAlias("count")  int count,
//    @JsonAlias("next")  String next,
//    @JsonAlias("previous")  String previous,
//    @JsonAlias("results") List<Libro> resultados
) { }

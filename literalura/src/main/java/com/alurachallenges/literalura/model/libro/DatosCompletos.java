package com.alurachallenges.literalura.model.libro;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DatosCompletos(
        @JsonAlias("results") List<DatosLibro> listaLibros
) {
}

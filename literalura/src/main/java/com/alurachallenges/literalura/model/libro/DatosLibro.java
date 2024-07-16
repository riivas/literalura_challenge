package com.alurachallenges.literalura.model.libro;

import com.alurachallenges.literalura.model.autor.DatosAutor;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record DatosLibro(

        @JsonAlias("results") List<DatosLibro> listaLibros,

        @JsonAlias("title") String titulo,

        @JsonAlias("authors") List<DatosAutor> autor,

        @JsonAlias("languages") String idiomas,

        @JsonAlias("download_count") Integer numeroDescargas
        ) {
}

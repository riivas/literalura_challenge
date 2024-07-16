package com.alurachallenges.literalura.model.libro;

import com.alurachallenges.literalura.model.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloContainsIgnoreCase(String tituloLibro);

    List<Autor> findByNombre();

    List<Libro> findByIdioma(Idioma idioma);

    @Query("SELECT a FROM Libro l JOIN l.autor a WHERE a.FechaNacimiento >= :fechaBuscada AND a.FechaFallecimiento <= :fechaMax")
    Optional<Autor> autoresVivosEntreFechas(Integer fechaMin, Integer fechaMax);
}

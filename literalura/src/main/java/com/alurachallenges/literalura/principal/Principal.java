package com.alurachallenges.literalura.principal;

import com.alurachallenges.literalura.model.autor.Autor;
import com.alurachallenges.literalura.model.libro.DatosLibro;
import com.alurachallenges.literalura.model.libro.Idioma;
import com.alurachallenges.literalura.model.libro.Libro;
import com.alurachallenges.literalura.model.libro.LibroRepository;
import com.alurachallenges.literalura.service.ConsumoAPI;
import com.alurachallenges.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Scanner teclado = new Scanner(System.in);

    private ConvierteDatos convierteDatos = new ConvierteDatos();

    private LibroRepository libroRepository;

    List<Autor> autores = new ArrayList<>();
    Optional<Libro> libroBuscado;
    Optional<Autor> autoresBuscados;


    public void MostrarMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    --------- M E N U ---------
                    1 - Buscar libros
                    2 - Buscar libros por titulo
                    3 - Buscar autores
                    4 - Listado de libros por idiomas
                    5 - Autores vivos en determinada fecha
                    
                    0 - SALIR
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibros();
                    break;

                case 2:
                    buscarLibroPorTitulo();
                    break;

                case 3:
                    buscarAutores();
                    break;

                case 4:
                    buscarLibrosPorIdioma();
                    break;

                case 5:
                    buscarAutoresVivosEntreFechas();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }


    private DatosLibro obtenerDatos() {
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = convierteDatos.obtenerDatos(json, DatosLibro.class);
        System.out.println(datosBusqueda);
        return datosBusqueda;
    }

    private void buscarLibros(){
        DatosLibro datosObtenidos = obtenerDatos();
        Libro libro = new Libro(datosObtenidos);
        libroRepository.save(libro);
    }

    public void buscarLibroPorTitulo(){
        System.out.println("Escribe la serie que deseas buscar: ");
        var tituloLibro = teclado.nextLine();
        libroBuscado = libroRepository.findByTituloContainsIgnoreCase(tituloLibro);

        if (libroBuscado.isPresent()){
            System.out.println("La serie buscada es: " + libroBuscado.get() + "\n");
        } else {
            System.out.println("Serie no encontrada.\n");
        }
    }

    public void buscarAutores(){
        autores.addAll(libroRepository.findByNombre());
        autores.forEach(autor -> System.out.println());
    }

    private void buscarLibrosPorIdioma() {
        System.out.println("Escriba el género/categoría de la serie que desea buscar: ");
        var idiomaBuscado = teclado.nextLine();
        var idioma = Idioma.fromString(idiomaBuscado);
        List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);
        System.out.println("Los libros en idioma " + idiomaBuscado);
        librosPorIdioma.forEach(System.out::println);
        System.out.println("\n");
    }

    public void buscarAutoresVivosEntreFechas(){
        System.out.println("Escriba el año minimo a buscar");
        var fechaMinima = teclado.nextInt();
        System.out.println("Escriba el año maximo a buscar");
        var fechaMaxima = teclado.nextInt();
        autoresBuscados = libroRepository.autoresVivosEntreFechas(fechaMinima, fechaMaxima);
        autores.forEach(autor -> System.out.println());
    }


}

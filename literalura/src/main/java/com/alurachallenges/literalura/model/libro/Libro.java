package com.alurachallenges.literalura.model.libro;

import com.alurachallenges.literalura.model.autor.Autor;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(unique = true)
    private String tituloLibro;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private int descargas;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;


//    Constructor

    public Libro(){};

    public Libro(DatosLibro datosLibro){
        if(datosLibro.titulo().isEmpty()){
            this.tituloLibro = "";
        } else {
            this.tituloLibro = datosLibro.titulo();
        }

        if(datosLibro.idiomas().isEmpty()){
            this.idioma = null;
        } else {
            this.idioma = Idioma.fromString(datosLibro.idiomas());
        }

        if(datosLibro.numeroDescargas() < 0){
            this.descargas = 0;
        } else {
            this.descargas = datosLibro.numeroDescargas();
        }
    }


//    Getters & Setters

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autores) {
        autores.forEach(a -> a.setLibro(this));
        this.autor = autores;
    }


//    toString

    @Override
    public String toString() {
        return "---------- Libro ----------" +
                "Título:'" + tituloLibro + '\'' +
                "Autor:" + autor.get(0).getApellido() + "," + autor.get(1).getNombre() + '\n' +
                "Idioma:" + idioma + '\n' +
                "Descargas: " + descargas;
    }
}

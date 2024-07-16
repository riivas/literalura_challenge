package com.alurachallenges.literalura.model.autor;

import com.alurachallenges.literalura.model.libro.Libro;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String nombre;

    private String apellido;

    private String FechaNacimiento;

    private String FechaFallecimiento;

    @ManyToOne
    private Libro libro;

    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        if (Objects.equals(datosAutor.nombre(), "")){
            this.nombre = "";
        } else {
            this.nombre = datosAutor.nombre().split(",")[1].trim();
            this.apellido = datosAutor.nombre().split(",")[0].trim();
        }

        if (Objects.equals(datosAutor.fechaNacimiento(), "")){
            this.FechaNacimiento = "";
        } else {
            this.FechaNacimiento = datosAutor.fechaNacimiento();
        }

        if (Objects.equals(datosAutor.fechaFallecimiento(), "")){
            this.FechaFallecimiento = "";
        } else {
            this.FechaFallecimiento = datosAutor.fechaFallecimiento();
        }



    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public String getFechaFallecimiento() {
        return FechaFallecimiento;
    }

    public void setFechaFallecimiento(String fechaFallecimiento) {
        FechaFallecimiento = fechaFallecimiento;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Autor: " + apellido + "," + nombre + '\'' +
                "FechaNacimiento: " + FechaNacimiento + '\'' +
                "FechaFallecimiento: " + FechaFallecimiento + '\'' +
                "libro: [" + libro + "]" ;
    }
}

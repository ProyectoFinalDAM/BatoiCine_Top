package es.cipfpbatoi.models.dto.prods;

import java.time.LocalDate;
import java.util.Set;

public class Produccion {
    private String id;
    private String titulo;
    private Calificacion calificacion;
    private LocalDate fecha_lanzamiento;
    private int duracion;
    private Set<String> genero;
    private String director;
    private Set<String> actores;
    private String guion;
    private String productora;
    private String poster;
    private Set<String> plataforma;
    private int visualizaciones;
    private String web;
    private Tipo tipo;

    public Produccion(String id, String titulo, Calificacion calificacion, LocalDate fecha_lanzamiento, int duracion, Set<String> genero, String director, Set<String> actores, String guion, String productora, String poster, Set<String> plataforma, String web, Tipo tipo) {
        this.id = id;
        this.titulo = titulo;
        this.calificacion = calificacion;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.duracion = duracion;
        this.genero = genero;
        this.director = director;
        this.actores = actores;
        this.guion = guion;
        this.productora = productora;
        this.poster = poster;
        this.plataforma = plataforma;
        this.web = web;
        this.tipo = tipo;
    }

    public void setVisualizaciones(int visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    @Override
    public String toString() {
        return "Produccion{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", calificacion=" + calificacion +
                ", fecha_lanzamiento=" + fecha_lanzamiento +
                ", duracion=" + duracion +
                ", genero=" + genero +
                ", director='" + director + '\'' +
                ", actores=" + actores +
                ", guion='" + guion + '\'' +
                ", productora='" + productora + '\'' +
                ", poster='" + poster + '\'' +
                ", plataforma=" + plataforma +
                ", web='" + web + '\'' +
                ", tipo=" + tipo +
                "}\n";
    }
}

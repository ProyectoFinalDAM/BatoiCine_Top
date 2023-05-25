package es.cipfpbatoi.models.dto.prods;

import java.time.LocalDate;
import java.util.Set;

public class Produccion {
    private String id;
    private String titulo;
    private Calificacion calificacion;
    private LocalDate fecha_lanzamiento;
    private int duracion;
    private int id_genero;
    private Set<String> actores;
    private String guion;
    private String productora;
    private String url_trailer;
    private String poster;
    private Set<String> plataforam;
    private int visualizaciones;
    private String web;
    private Tipo tipo;

    public Produccion(String id, String titulo, Calificacion calificacion, LocalDate fecha_lanzamiento, int duracion, int id_genero, Set<String> actores, String guion, String productora, String url_trailer, String poster, Set<String> plataforam, int visualizaciones, String web, Tipo tipo) {
        this.id = id;
        this.titulo = titulo;
        this.calificacion = calificacion;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.duracion = duracion;
        this.id_genero = id_genero;
        this.actores = actores;
        this.guion = guion;
        this.productora = productora;
        this.url_trailer = url_trailer;
        this.poster = poster;
        this.plataforam = plataforam;
        this.visualizaciones = visualizaciones;
        this.web = web;
        this.tipo = tipo;
    }
}

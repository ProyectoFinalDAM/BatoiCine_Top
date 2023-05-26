package es.cipfpbatoi.models.dto.prods;

import java.sql.Date;
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
        this.visualizaciones = 0;
        this.web = web;
        this.tipo = tipo;
    }

    public Produccion(String id, String titulo, Calificacion calificacion, LocalDate fecha_lanzamiento, int duracion, Set<String> genero, String director, Set<String> actores, String guion, String productora, String poster, Set<String> plataforma, int visualizaciones, String web, Tipo tipo) {
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
        this.visualizaciones = visualizaciones;
        this.web = web;
        this.tipo = tipo;
    }

    public void setVisualizaciones(int visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Calificacion getCalificacion() {
        return calificacion;
    }

    public LocalDate getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    public int getDuracion() {
        return duracion;
    }

    public Set<String> getGenero() {
        return genero;
    }

    public Set<String> getActores() {
        return actores;
    }

    public String getGuion() {
        return guion;
    }

    public String getProductora() {
        return productora;
    }

    public String getPoster() {
        return poster;
    }

    public Set<String> getPlataforma() {
        return plataforma;
    }

    public int getVisualizaciones() {
        return visualizaciones;
    }

    public String getWeb() {
        return web;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getDirector() {
        return director;
    }
}

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
    private Set<String> actores;
    private String guion;
    private String productora;
    private String url_trailer;
    private String poster;
    private Set<String> plataforma;
    private int visualizaciones;
    private String web;
    private Tipo tipo;

    public Produccion(String id, String titulo, Calificacion calificacion, LocalDate fecha_lanzamiento, int duracion, Set<String> genero, Set<String> actores, String guion, String productora, String url_trailer, String poster, Set<String> plataforma, int visualizaciones, String web, Tipo tipo) {
        this.id = id;
        this.titulo = titulo;
        this.calificacion = calificacion;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.duracion = duracion;
        this.genero = genero;
        this.actores = actores;
        this.guion = guion;
        this.productora = productora;
        this.url_trailer = url_trailer;
        this.poster = poster;
        this.plataforma = plataforma;
        this.visualizaciones = visualizaciones;
        this.web = web;
        this.tipo = tipo;
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

    public String getUrl_trailer() {
        return url_trailer;
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


}

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
    private String guion;
    private String productora;
    private String poster;
    private Set<String> plataforma;
    private int visualizaciones;
    private String web;
    private Tipo tipo;

    public Produccion(String id) {
        this.id = id;
    }

    public Produccion(String id, String titulo, Calificacion calificacion, LocalDate fecha_lanzamiento, int duracion, Set<String> genero, String director, String guion, String productora, String poster, Set<String> plataforma, String web, Tipo tipo) {
        this.id = id;
        this.titulo = titulo;
        this.calificacion = calificacion;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.duracion = duracion;
        this.genero = genero;
        this.director = director;
        this.guion = guion;
        this.productora = productora;
        this.poster = poster;
        this.plataforma = plataforma;
        this.visualizaciones = 0;
        this.web = web;
        this.tipo = tipo;
    }

    public Produccion(String id, String titulo, Calificacion calificacion, LocalDate fecha_lanzamiento, int duracion, Set<String> genero, String director, String guion, String productora, String poster, Set<String> plataforma, int visualizaciones, String web, Tipo tipo) {
        this.id = id;
        this.titulo = titulo;
        this.calificacion = calificacion;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.duracion = duracion;
        this.genero = genero;
        this.director = director;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCalificacion(Calificacion calificacion) {
        this.calificacion = calificacion;
    }

    public void setFecha_lanzamiento(LocalDate fecha_lanzamiento) {
        this.fecha_lanzamiento = fecha_lanzamiento;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setGenero(Set<String> genero) {
        this.genero = genero;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGuion(String guion) {
        this.guion = guion;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setPlataforma(Set<String> plataforma) {
        this.plataforma = plataforma;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setTipo(Tipo tipo) {
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

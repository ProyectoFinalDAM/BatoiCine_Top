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
}

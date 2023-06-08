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

    /**
     * Inserta las visualizaciones en una producción
     * @author Marcos Sanz
     * @param visualizaciones
     */

    public void setVisualizaciones(int visualizaciones) {
        this.visualizaciones = visualizaciones;
    }

    /**
     * Inserta el título en una producción
     * @author Marcos Sanz
     * @param titulo
     */

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Inserta la calificación en una producción
     * @author Marcos Sanz
     * @param calificacion
     */

    public void setCalificacion(Calificacion calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Inserta la fecha de lanzamiento en una producción
     * @author Marcos Sanz
     * @param fecha_lanzamiento
     */

    public void setFecha_lanzamiento(LocalDate fecha_lanzamiento) {
        this.fecha_lanzamiento = fecha_lanzamiento;
    }

    /**
     * Inserta la duración en una producción
     * @author Marcos Sanz
     * @param duracion
     */

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Inserta una lista de los géneros que aparecen en una producción
     * @author Marcos Sanz
     * @param genero
     */

    public void setGenero(Set<String> genero) {
        this.genero = genero;
    }

    /**
     * Inserta el director en una producción
     * @author Marcos Sanz
     * @param director
     */

    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Inserta el guion en una producción
     * @author Marcos Sanz
     * @param guion
     */

    public void setGuion(String guion) {
        this.guion = guion;
    }

    /**
     * Inserta la porductora en una producción
     * @author Marcos Sanz
     * @param productora
     */

    public void setProductora(String productora) {
        this.productora = productora;
    }

    /**
     * Inserta el poster en una producción
     * @author Marcos Sanz
     * @param poster
     */

    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * Inserta las plataformas en una producción
     * @author Marcos Sanz
     * @param plataforma
     */

    public void setPlataforma(Set<String> plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * Inserta la web en una producción
     * @author Marcos Sanz
     * @param web
     */

    public void setWeb(String web) {
        this.web = web;
    }

    /**
     * Inserta el tipo en una producción
     * @author Marcos Sanz
     * @param tipo
     */

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve el id de la produccíon
     */

    public String getId() {
        return id;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve el título de la produccíon
     */

    public String getTitulo() {
        return titulo;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve la calificación de la produccíon
     */

    public Calificacion getCalificacion() {
        return calificacion;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve la fehca de lanzamineto de la produccíon
     */

    public LocalDate getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve la duración de la produccíon
     */

    public int getDuracion() {
        return duracion;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve los géneros de la produccíon
     */

    public Set<String> getGenero() {
        return genero;
    }


    /**
     * @author Pablo Marin
     * @return Devuelve el guion de la produccíon
     */

    public String getGuion() {
        return guion;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve la productora de la produccíon
     */

    public String getProductora() {
        return productora;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve el poster de la produccíon
     */

    public String getPoster() {
        return poster;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve las produccion de la produccíon
     */

    public Set<String> getPlataforma() {
        return plataforma;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve las visualizaciones de la produccíon
     */

    public int getVisualizaciones() {
        return visualizaciones;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve la web de la produccíon
     */

    public String getWeb() {
        return web;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve el tipo de la produccíon
     */

    public Tipo getTipo() {
        return tipo;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve el director de la produccíon
     */

    public String getDirector() {
        return director;
    }

    public String getPlataformasFormat(){
        String plataformas = getPlataforma().toString();
        plataformas = plataformas.replaceAll( "\\[", " " );
        plataformas = plataformas.replaceAll( "]", "" );
        return plataformas;
    }

}

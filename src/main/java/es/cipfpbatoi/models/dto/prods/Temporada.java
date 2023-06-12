package es.cipfpbatoi.models.dto.prods;

public class Temporada {
    private String pelicula;
    private int temporada;
    private int anyoLanzamiento;
    private String guion;
    private int capitulos;

    public Temporada(String pelicula, int temporada, int anyoLanzamiento, String guion, int capitulos) {
        this.pelicula        = pelicula;
        this.temporada       = temporada;
        this.anyoLanzamiento = anyoLanzamiento;
        this.guion           = guion;
        this.capitulos       = capitulos;
    }

    /**
     * @author Andreu Francés
     * @return Devuelve el id de la pelicula
     */

    public String getPelicula() {
        return pelicula;
    }

    /**
     * @author Andreu Francés
     * @return Devuelve el numero de temporada
     */

    public int getTemporada() {
        return temporada;
    }

    /**
     * @author Andreu Francés
     * @return Devuelve el anyo de lanzamineto
     */

    public int getAnyoLanzamiento() {
        return anyoLanzamiento;
    }

    /**
     * @author Andreu Francés
     * @return Devuelve el guión de la pelicula
     */

    public String getGuion() {
        return guion;
    }

    /**
     * @author Andreu Francés
     * @return Devuelve el número de capítulos
     */

    public int getCapitulos() {
        return capitulos;
    }

    /**
     * @author Martín Peidro
     * @return Devuelve el string de algunos campos de la pelicula
     */

    @Override
    public String toString() {
        return this.pelicula + "";
    }
}

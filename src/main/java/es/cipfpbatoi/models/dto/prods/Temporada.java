package es.cipfpbatoi.models.dto.prods;

public class Temporada {
    private int pelicula;
    private int temporada;
    private int anyoLanzamiento;
    private String guion;
    private int capitulos;

    public Temporada(int pelicula, int temporada, int anyoLanzamiento, String guion, int capitulos) {
        this.pelicula        = pelicula;
        this.temporada       = temporada;
        this.anyoLanzamiento = anyoLanzamiento;
        this.guion           = guion;
        this.capitulos       = capitulos;
    }

    public int getPelicula() {
        return pelicula;
    }

    public int getTemporada() {
        return temporada;
    }

    public int getAnyoLanzamiento() {
        return anyoLanzamiento;
    }

    public String getGuion() {
        return guion;
    }

    public int getCapitulos() {
        return capitulos;
    }

    @Override
    public String toString() {
        return this.pelicula + "";
    }
}

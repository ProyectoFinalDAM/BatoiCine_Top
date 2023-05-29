package es.cipfpbatoi.models.dto.prods;

public class Ranking {
    private String id_produccion;
    private int puntos;

    public Ranking(String id_produccion, int puntos) {
        this.id_produccion = id_produccion;
        this.puntos = puntos;
    }

    public String getId_produccion() {
        return id_produccion;
    }

    public int getPuntos() {
        return puntos;
    }
}

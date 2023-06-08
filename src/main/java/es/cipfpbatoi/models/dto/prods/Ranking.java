package es.cipfpbatoi.models.dto.prods;

public class Ranking {
    private String id_produccion;
    private int puntos;

    public Ranking(String id_produccion, int puntos) {
        this.id_produccion = id_produccion;
        this.puntos = puntos;
    }

    /**
     * @author Andreu Francés
     * @return Devuelve el id de la producción
     */

    public String getId_produccion() {
        return id_produccion;
    }

    /**
     * @author Andreu Francés
     * @return Devuelve la puntuación de la produccíon
     */

    public int getPuntos() {
        return puntos;
    }
}

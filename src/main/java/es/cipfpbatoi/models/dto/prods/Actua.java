package es.cipfpbatoi.models.dto.prods;

public class Actua {

    private String idActor;

    private String idProduccion;

    public Actua(String idActor, String idProduccion) {
        this.idActor = idActor;
        this.idProduccion = idProduccion;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve el id del actor
     */

    public String getIdActor() {
        return idActor;
    }

    /**
     * @author Pablo Marin
     * @return Devuelve el id de la producción
     */

    public String getIdProduccion() {
        return idProduccion;
    }
}

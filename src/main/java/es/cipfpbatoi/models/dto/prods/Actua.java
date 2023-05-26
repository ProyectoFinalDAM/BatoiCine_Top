package es.cipfpbatoi.models.dto.prods;

public class Actua {

    private String idActor;

    private String idProduccion;

    public Actua(String idActor, String idProduccion) {
        this.idActor = idActor;
        this.idProduccion = idProduccion;
    }

    public String getIdActor() {
        return idActor;
    }

    public String getIdProduccion() {
        return idProduccion;
    }
}

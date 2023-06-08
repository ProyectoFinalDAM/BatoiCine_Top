package es.cipfpbatoi.models.dto.prods;

public class Actor {
    private String id;
    private String nombre;

    public Actor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * @uthor Pablo Marin
     * @return Devuelve el id del actor
     */

    public String getId() {
        return id;
    }

    /**
     * @uthor Pablo Marin
     * @return Devuelve el nombre del actor
     */

    public String getNombre() {
        return nombre;
    }

}

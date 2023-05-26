package es.cipfpbatoi.models.dto.prods;

public class Actor {
    private String id;
    private String nombre;

    public Actor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}

package es.cipfpbatoi.models.dto.prods;

public class Visualizar {
    private String id_produccion;
    private String id_usuario;

    public Visualizar(String id_produccion, String id_usuario) {
        this.id_produccion = id_produccion;
        this.id_usuario = id_usuario;
    }

    public String getId_produccion() {
        return id_produccion;
    }

    public String getId_usuario() {
        return id_usuario;
    }
}

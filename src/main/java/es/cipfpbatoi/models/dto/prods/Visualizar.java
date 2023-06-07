package es.cipfpbatoi.models.dto.prods;

public class Visualizar {
    private String id_produccion;
    private int id_usuario;

    public Visualizar(String id_produccion, int id_usuario) {
        this.id_produccion = id_produccion;
        this.id_usuario = id_usuario;
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
     * @return Devuelve el id del usuario
     */

    public int getId_usuario() {
        return id_usuario;
    }
}

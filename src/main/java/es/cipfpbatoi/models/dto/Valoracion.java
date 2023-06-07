package es.cipfpbatoi.models.dto;

public class Valoracion {

    private String id_produccion;

    private String id_usuario;

    private int nota;

    private String comentario;

    public Valoracion(String id_produccion, String id_usuario, int nota, String comentario) {
        this.id_produccion = id_produccion;
        this.id_usuario = id_usuario;
        this.nota = nota;
        this.comentario = comentario;
    }

    /**
     * @author Pablo Marin
     * @return devuelve el id de la producción
     */

    public String getId_produccion() {
        return id_produccion;
    }

    /**
     * @author Pablo Marin
     * @return devuelve el id del usuario
     */

    public String getId_usuario() {
        return id_usuario;
    }

    /**
     * @author Pablo Marin
     * @return devuelve la nota de la producción
     */

    public int getNota() {
        return nota;
    }

    /**
     * @author Pablo Marin
     * @return devuelve el comentario de la producción
     */

    public String getComentario() {
        return comentario;
    }
}

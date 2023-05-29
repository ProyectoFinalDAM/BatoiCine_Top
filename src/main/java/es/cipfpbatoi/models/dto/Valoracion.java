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

    public String getId_produccion() {
        return id_produccion;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }
}

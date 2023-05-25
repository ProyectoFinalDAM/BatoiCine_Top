package es.cipfpbatoi.models.dto;

public class User {
    private String id;
    private String nombre;
    private String apellidos;
    private String contrasenya;

    public User(String id, String nombre, String apellidos, String contrasenya) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasenya = contrasenya;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getContrasenya() {
        return contrasenya;
    }
}

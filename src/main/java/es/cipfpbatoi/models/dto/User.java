package es.cipfpbatoi.models.dto;

public class User {
    private int id;
    private String nombre;
    private String contrasenya;

    public User(int id, String nombre, String contrasenya) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenya = contrasenya;
    }

    public User(int id) {
        this.id= id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }


    public String getContrasenya() {
        return contrasenya;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}

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

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }


    public String getContrasenya() {
        return contrasenya;
    }
}

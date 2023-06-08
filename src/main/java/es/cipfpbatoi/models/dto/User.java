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


    /**
     * @author Andreu Francés
     * @return devuelve el id del usuario
     */

    public int getId() {
        return id;
    }

    /**
     * @author Andreu Francés
     * @return devuelve el nombre del usuario
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * @author Andreu Francés
     * @return devuelve la contraseña del usuario
     */

    public String getContrasenya() {
        return contrasenya;
    }

    /**
     * Cambia el nombre al usuario
     * @author Marcos Sanz
     * @param nombre
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Cambia la contrasenya al usuario
     * @author Marcos Sanz
     * @param contrasenya
     */

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

}

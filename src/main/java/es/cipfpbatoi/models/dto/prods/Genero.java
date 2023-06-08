package es.cipfpbatoi.models.dto.prods;

public class Genero {
    private int id;
    private String cod;
    private String descripcion;

    public Genero(int id, String cod, String descripcion) {
        this.id = id;
        this.cod = cod;
        this.descripcion = descripcion;
    }

    /**
     * @author Andreu Francés
     * @return Devuelve el id del género
     */

    public int getId() {
        return id;
    }

    /**
     * @author Andreu Francés
     * @return Devuelve el código del género
     */

    public String getCod() {
        return cod;
    }

    /**
     * @author Andreu Francés
     * @return Devuelve la descripción del género
     */

    public String getDescripcion() {
        return descripcion;
    }


    /**
     * @author Martín Peidro
     * @return Devuelve el string de algunos campos del género
     */

    @Override
    public String toString() {
        return this.descripcion;

    }
}

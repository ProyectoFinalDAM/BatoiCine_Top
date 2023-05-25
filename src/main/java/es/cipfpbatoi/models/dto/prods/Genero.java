package es.cipfpbatoi.models.dto.prods;

public class Genero {
    private int id;
    private String cod;
    private String descripcion;

    public Genero(int id, String cod, String descripcion) {
 andreuFrancés
        this.id = id;
        this.cod = cod;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getCod() {
        return cod;
    }

    public String getDescripcion() {
        return descripcion;

        this.id          = id;
        this.cod         = cod;
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return this.id + this.cod;
 main
    }
}

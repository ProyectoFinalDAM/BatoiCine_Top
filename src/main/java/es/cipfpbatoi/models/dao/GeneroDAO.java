package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.models.dto.prods.Genero;

import java.util.ArrayList;

public interface GeneroDAO {

    /**
     * Reune en una lista todos los géneros de la base de datos
     * @author Martin Peidro
     * @return ArrayList de género, devuelve todos los generos encontrados
     */

    ArrayList<Genero> findAll();

    /**
     * Guarda en la base de datos nuevos generos pasados como parametros
     * @author Martin Peidro
     * @param genero
     */

    void save(Genero genero);
}

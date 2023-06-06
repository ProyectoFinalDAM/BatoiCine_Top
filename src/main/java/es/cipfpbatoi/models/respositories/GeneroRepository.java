package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.models.dao.GeneroDAO;
import es.cipfpbatoi.models.dto.prods.Genero;

import java.util.ArrayList;

/**
 * Utiliza los métodos dao de para completar las clases necesarias para implementarlas mas tardes en los controller
 */

public class GeneroRepository {
    private GeneroDAO generoDAO;

    public GeneroRepository(GeneroDAO generoDAO) {
        this.generoDAO = generoDAO;
    }

    /**
     * Reune en una lista todos los géneros de la base de datos
     * @author Marcos Sanz
     * @return ArrayList de genero, devuelve todos los generos encontrados
     */

    public ArrayList<Genero> findAll(){
        return generoDAO.findAll();
    }

    /**
     * Guarda en la base de datos nuevos generos pasados como parametros
     * @author Marcos Sanz
     * @param genero
     */

    public void save(Genero genero){
        generoDAO.save(genero);
    }
}

package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.prods.Actor;

import java.util.ArrayList;

public interface ActorDAO {

    /**
     * Busca todos los actores
     * @author Pablo Marin
     * @return Una lista de los actores
     * @throws DatabaseErrorException
     */

    ArrayList<Actor> findAll() throws DatabaseErrorException;

    /**
     * Gurada un actor
     * @author Pablo Marin
     * @param actor
     * @throws DatabaseErrorException
     */

    void save(Actor actor) throws DatabaseErrorException;
}

package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.prods.Actor;

import java.util.ArrayList;

public interface ActorDAO {

    ArrayList<Actor> findAll() throws DatabaseErrorException;

    void save(Actor actor) throws DatabaseErrorException;
}

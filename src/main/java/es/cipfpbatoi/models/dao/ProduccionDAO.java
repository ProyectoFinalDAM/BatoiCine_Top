package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface ProduccionDAO {

    ArrayList<Produccion> findAll() throws DatabaseErrorException;
    void save(Produccion produccion) throws DatabaseErrorException;

    Produccion getById (String dni) throws NotFoundException, DatabaseErrorException;

}

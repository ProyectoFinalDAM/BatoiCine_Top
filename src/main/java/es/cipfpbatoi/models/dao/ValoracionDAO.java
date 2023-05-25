package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface ValoracionDAO {

    ArrayList<Valoracion> findAll() throws DatabaseErrorException;

    void save(Valoracion valoracion) throws DatabaseErrorException;
}

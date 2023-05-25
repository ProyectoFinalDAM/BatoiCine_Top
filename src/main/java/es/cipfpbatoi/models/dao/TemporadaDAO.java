package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Temporada;

import java.util.ArrayList;

public interface TemporadaDAO {
    ArrayList<Temporada> findAll() throws DatabaseErrorException;
    void save(Temporada temporada) throws DatabaseErrorException;
}

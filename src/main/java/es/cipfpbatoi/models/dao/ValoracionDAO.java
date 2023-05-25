package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface ValoracionDAO {
    ArrayList<Valoracion> findAll();
    void save(Valoracion valoracion);
}

package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface ProduccionDAO {
    ArrayList<Produccion> findAll();
    void save(Produccion produccion);

}

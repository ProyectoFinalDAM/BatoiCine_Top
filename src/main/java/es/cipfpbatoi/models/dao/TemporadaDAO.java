package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Temporada;

import java.util.ArrayList;

public interface TemporadaDAO {
    ArrayList<Temporada> findAll();
    void save(Produccion produccion);
}

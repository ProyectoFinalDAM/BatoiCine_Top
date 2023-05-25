package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface GeneroDAO {
    ArrayList<Genero> findAll();
    void save(Genero genero);
}

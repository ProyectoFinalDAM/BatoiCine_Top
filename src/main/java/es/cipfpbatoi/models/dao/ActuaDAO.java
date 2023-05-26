package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.models.dto.prods.Actua;

import java.util.ArrayList;

public interface ActuaDAO {

    ArrayList<Actua> findAll();

    void save (Actua actua);
}

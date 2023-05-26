package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Visualizar;

import java.util.ArrayList;

public interface VisualizarDAO {
    ArrayList<User> getProdUsers(Produccion produccion) throws DatabaseErrorException;

    ArrayList<User> getProdProducciones(Produccion produccion) throws DatabaseErrorException;
    void save(Visualizar visualizar) throws DatabaseErrorException;

}

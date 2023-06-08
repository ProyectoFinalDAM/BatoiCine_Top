package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.prods.Actua;

import java.util.ArrayList;

public interface ActuaDAO {

    /**
     * Busca todas las Actuaciones
     * @author Pablo Marin
     * @return Una lista de las actuaciones
     * @throws DatabaseErrorException
     */

    ArrayList<Actua> findAll();

    /**
     * Gurada una actuación
     * @author Pablo Marin
     * @param actua
     * @throws DatabaseErrorException
     */

    void save (Actua actua);
}

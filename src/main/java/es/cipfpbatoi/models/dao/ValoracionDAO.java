package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.Valoracion;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface ValoracionDAO {

    /**
     * Busca todas las valoraciones junto a sus respectivas notas
     * @author Pablo Marin
     * @return Una lista de las producciones con su nota
     * @throws DatabaseErrorException
     */

    ArrayList<Valoracion> findAll() throws DatabaseErrorException;

    /**
     * Gurada una valoración
     * @author Pablo Marin
     * @param valoracion
     * @throws DatabaseErrorException
     */

    void save(Valoracion valoracion) throws DatabaseErrorException;
}

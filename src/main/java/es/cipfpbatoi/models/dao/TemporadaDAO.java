package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Temporada;

import java.util.ArrayList;

public interface TemporadaDAO {

    /**
     * Busca todos los elementos de las temporadas
     * @author Andreu Francés
     * @return Una lista de todos los episodios de la temporada
     * @throws DatabaseErrorException
     */

    ArrayList<Temporada> findAll() throws DatabaseErrorException;

    /**
     * Gurada en la base de datos todos los datos sobre las temporadas
     * @author Andreu Francés
     * @param temporada
     * @throws DatabaseErrorException
     */

    void save(Temporada temporada) throws DatabaseErrorException;

    Temporada getByIdProdTemporada(String id_produccion, int temporada) throws DatabaseErrorException, NotFoundException;
}

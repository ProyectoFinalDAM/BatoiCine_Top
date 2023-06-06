package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Ranking;

import java.util.ArrayList;
import java.util.Random;

public interface RankingDAO {

    /**
     * Busca todos los elementos del ranking
     * @author Andreu Francés
     * @return Una lista del resultado del ranking
     * @throws DatabaseErrorException
     */

    ArrayList<Ranking> findAll() throws DatabaseErrorException;

    /**
     * Guarda en la base de datos todos los datos sobre el ranking
     * @author Andreu Francés
     * @param ranking
     * @throws DatabaseErrorException
     */

    void save(Ranking ranking) throws DatabaseErrorException;
}

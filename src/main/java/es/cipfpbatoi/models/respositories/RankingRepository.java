package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dto.prods.Ranking;

import java.util.ArrayList;

/**
 * Utiliza los métodos dao de para completar las clases necesarias para implementarlas mas tardes en los controller
 */

public class RankingRepository  {
    RankingDAO rankingDAO;

    public RankingRepository(RankingDAO rankingDAO) {
        this.rankingDAO = rankingDAO;
    }

    /**
     * Busca todos los elementos del ranking
     * @author Andreu Francés
     * @return Una lista del resultado del ranking
     * @throws DatabaseErrorException
     */

    public ArrayList<Ranking> findAll() throws DatabaseErrorException {
        return rankingDAO.findAll();
    }

    /**
     * Guarda en la base de datos todos los datos sobre el ranking
     * @author Andreu Francés
     * @param ranking
     * @throws DatabaseErrorException
     */

    public void save(Ranking ranking) throws DatabaseErrorException {
        rankingDAO.save(ranking);
    }
}

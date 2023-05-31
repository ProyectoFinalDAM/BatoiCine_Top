package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dto.prods.Ranking;

import java.util.ArrayList;

public class RankingRepository  {
    RankingDAO rankingDAO;

    public RankingRepository(RankingDAO rankingDAO) {
        this.rankingDAO = rankingDAO;
    }

    public ArrayList<Ranking> findAll() throws DatabaseErrorException {
        return rankingDAO.findAll();
    }

    public void save(Ranking ranking) throws DatabaseErrorException {
        rankingDAO.save(ranking);
    }
}

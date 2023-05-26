package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Ranking;

import java.util.ArrayList;
import java.util.Random;

public interface RankingDAO {
    ArrayList<Ranking> findAll() throws DatabaseErrorException;
    void save(Ranking ranking) throws DatabaseErrorException;
}

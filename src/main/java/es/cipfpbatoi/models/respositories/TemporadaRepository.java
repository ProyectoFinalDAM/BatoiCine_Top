package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.TemporadaDAO;
import es.cipfpbatoi.models.dto.prods.Temporada;

import java.util.ArrayList;

public class TemporadaRepository {
    private TemporadaDAO temporadaDAO;

    public TemporadaRepository(TemporadaDAO temporadaDAO) {
        this.temporadaDAO = temporadaDAO;
    }
    ArrayList<Temporada> findAll() throws DatabaseErrorException{
        return temporadaDAO.findAll();
    }
    void save(Temporada temporada) throws DatabaseErrorException{
        temporadaDAO.save(temporada);
    }
}

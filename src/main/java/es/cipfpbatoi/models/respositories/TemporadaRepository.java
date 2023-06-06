package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.TemporadaDAO;
import es.cipfpbatoi.models.dto.prods.Temporada;

import java.util.ArrayList;

    /**
    * Utiliza los métodos dao de para completar las clases necesarias para implementarlas mas tardes en los controller
    */

public class TemporadaRepository {
    private TemporadaDAO temporadaDAO;


    public TemporadaRepository(TemporadaDAO temporadaDAO) {
        this.temporadaDAO = temporadaDAO;
    }


        /**
         * Busca todos los elementos de las temporadas
         * @author Andreu Francés
         * @return Una lista de todos los episodios de la temporada
         * @throws DatabaseErrorException
         */

    ArrayList<Temporada> findAll() throws DatabaseErrorException{
        return temporadaDAO.findAll();
    }


        /**
         * Gurada en la base de datos todos los datos sobre las temporadas
         * @author Andreu Francés
         * @param temporada
         * @throws DatabaseErrorException
         */

    void save(Temporada temporada) throws DatabaseErrorException{
        temporadaDAO.save(temporada);
    }
}

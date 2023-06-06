package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dto.Valoracion;

import java.util.ArrayList;

    /**
     * Utiliza los métodos dao de para completar las clases necesarias para implementarlas mas tardes en los controller
    */

public class ValoracionRepository {
    ValoracionDAO valoracionDAO;

    public ValoracionRepository(ValoracionDAO valoracionDAO) {
        this.valoracionDAO = valoracionDAO;
    }

        /**
         * Busca todas las valoraciones junto a sus respectivas notas
         * @author Andreu Francés
         * @return Una lista de las producciones con su nota
         * @throws DatabaseErrorException
         */

    public ArrayList<Valoracion> findAll() throws DatabaseErrorException {
        return valoracionDAO.findAll();
    }

        /**
         * Gurada una valoración
         * @author Andreu Francés
         * @param valoracion
         * @throws DatabaseErrorException
         */

    public void save(Valoracion valoracion) throws DatabaseErrorException {
        valoracionDAO.save(valoracion);
    }
}

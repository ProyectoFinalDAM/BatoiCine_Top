package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.VisualizarDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Visualizar;

import java.util.ArrayList;

    /**
     * Utiliza los métodos dao de para completar las clases necesarias para implementarlas mas tardes en los controller
    */

public class VisualizarRepository {
    private VisualizarDAO visualizarDAO;

    public VisualizarRepository(VisualizarDAO visualizarDAO) {
        this.visualizarDAO = visualizarDAO;
    }

        /**
         * Busca en la base de datos las coincidencias de usuarios que han visto la pelicula pasada como parametro
         * @author Marcos Sanz
         * @param id_produccion
         * @return Retorna una lista de los usuarios que han visualiado la producción
         * @throws DatabaseErrorException
         */

    public ArrayList<User> getProdUsers(String id_produccion) throws DatabaseErrorException{
        return visualizarDAO.getProdUsers(id_produccion);
    }

        /**
         * Busca en la base de datos las coincidencias de producciones que ha visto cierto usuario
         * @param id_user
         * @return Retorna una lista de las producciones que ha visualiado el usuario
         * @throws DatabaseErrorException
         */

    ArrayList<Produccion> getUserProducciones(int id_user) throws DatabaseErrorException{
        return visualizarDAO.getUserProducciones(id_user);
    }

        /**
         * Guarda en la base de datos una visualizacion
         * @author Marcos Sanz
         * @param visualizar
         * @throws DatabaseErrorException
         */
    public void save(Visualizar visualizar) throws DatabaseErrorException{
        visualizarDAO.save(visualizar);
    }
}

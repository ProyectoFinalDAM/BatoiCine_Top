package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Visualizar;

import java.util.ArrayList;

public interface VisualizarDAO {


    /**
     * Busca en la base de datos las coincidencias de usuarios que han visto la pelicula pasada como parametro
     * @author Marcos Sanz
     * @param id_produccion
     * @return Retorna una lista de los usuarios que han visualiado la producción
     * @throws DatabaseErrorException
     */

    ArrayList<User> getProdUsers(String id_produccion) throws DatabaseErrorException;

    void sumarVisualizacion(String id_produccion) throws DatabaseErrorException;

    /**
     * Busca en la base de datos las coincidencias de producciones que ha visto cierto usuario
     * @author Andreu Francés
     * @param id_user
     * @return Retorna una lista de las producciones que ha visualiado el usuario
     * @throws DatabaseErrorException
     */

    ArrayList<Produccion> getUserProducciones(int id_user) throws DatabaseErrorException;

    /**
     * Guarda en la base de datos una visualizacion
     * @author Andreu Francés
     * @param visualizar
     * @throws DatabaseErrorException
     */

    void save(Visualizar visualizar) throws DatabaseErrorException;

}

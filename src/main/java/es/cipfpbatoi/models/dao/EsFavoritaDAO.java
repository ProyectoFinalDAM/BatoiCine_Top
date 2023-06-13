package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface EsFavoritaDAO {

    /**
     * Recibe un usuario para recibir una lista de sus producciones favoritas
     * @author Marcos Sanz
     * @param user
     * @return ArrayList de producciones
     * @throws DatabaseErrorException
     */

    ArrayList<Produccion> getUserFavs(User user) throws DatabaseErrorException;

    /**
     * Busca a través de la producción proporcionada los usuario que la tienen en favoritas o los actualiza
     * @author Marcos Sanz
     * @param produccion
     * @return ArrayList de usuarios
     * @throws DatabaseErrorException
     */

    ArrayList<User> getProdUsers(Produccion produccion) throws DatabaseErrorException;

    /**
     * Registra en la lista del usuario las producciones que ha seleccionado como favorita
     * @author Marcos Sanz
     * @param user
     * @param produccion
     */

    void save(User user, Produccion produccion);

    /**
     * ELimina en la lista del usuario las producciones que ha seleccionado como favorita
     * @author Andreu Francés
     * @param user
     * @param produccion
     * @throws DatabaseErrorException
     */

    void eliminar(User user, Produccion produccion) throws DatabaseErrorException;

}

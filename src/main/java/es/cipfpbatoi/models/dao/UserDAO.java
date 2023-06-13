package es.cipfpbatoi.models.dao;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;

import java.util.ArrayList;

public interface UserDAO {

    /**
     * Busca todos los usuarios del programa
     * @author Marcos Sanz
     * @return Una lista de todos los usuarios
     */

    ArrayList<User> findAll();

    /**
     * Guarda el usuario en la base de datos
     * @author Andreu Francés
     * @param user
     */

    void save(User user);

    /**
     * Recoge el ultimo código del usuario.
     * @author Marcos Sanz
     * @return el ultimo código del usuario
     */

    int getLastCod();

    /**
     * Recoge un usuario que coincide con el id pasado como parametro
     * @author Marcos Sanz
     * @param id
     * @return Un usuario
     * @throws UserNotExistException
     */

    User getById(int id) throws UserNotExistException;

    /**
     * Válida al usuario que coincide con la contraseña que tiene
     * @author Marcos Sanz
     * @param name
     * @param password
     * @return Un boolenano para saber si es correcto o no
     */

    boolean validUser(String name, String password);

    /**
     * Recoge el usuario que coincide con el nombre y la contraseña
     * @author Andreu Francés
     * @param name
     * @param password
     * @return Devuleve el usuario que coincide con el nombre y la contraseña
     * @throws UserNotExistException
     */

    User getUser(String name, String password) throws UserNotExistException;

}

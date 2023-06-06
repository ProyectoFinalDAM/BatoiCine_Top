package es.cipfpbatoi.models.respositories;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dao.UserDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Utiliza los métodos dao de para completar las clases necesarias para implementarlas mas tardes en los controller
 */

public class UserRepository {
    private UserDAO userDAO;

    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Busca todos los usuarios del programa
     * @author Marcos Sanz
     * @return Una lista de todos los usuarios
     * @throws DatabaseErrorException
     */

    public ArrayList<User> findAll() {
        return userDAO.findAll();
    }

    /**
     * Guarda el usuario en la base de datos
     * @author Marcos Sanz
     * @param user
     */

    public void save(User user) {
        userDAO.save(user);
    }

    /**
     * Válida al usuario que coincide con la contraseña que tiene
     * @author Marcos Sanz
     * @param name
     * @param password
     * @return Un boolenano para saber si es correcto o no
     */

    public boolean validUser(String name, String password) {
        return userDAO.validUser(name, password);
    }

    /**
     * Recoge un usuario que coincide con el id pasado como parametro
     * @author Marcos Sanz
     * @param id
     * @return Un usuario
     * @throws UserNotExistException
     */

    public User getById(int id) throws UserNotExistException{
        return userDAO.getById(id);
    }

    /**
     * Recoge el ultimo código del usuario.
     * @author Marcos Sanz
     * @return el ultimo codigo del usuario
     */

    public int getLastCod(){
        return userDAO.getLastCod();

    }

    /**
     * Recoge el usuario que coincide con el nombre y la contraseña
     * @author Andreu Francés
     * @param name
     * @param password
     * @return Devuleve el usuario que coincide con el nombre y la contraseña
     * @throws UserNotExistException
     */

    public User getUser(String name, String password) throws UserNotExistException {
        return userDAO.getUser(name, password);
    }


}

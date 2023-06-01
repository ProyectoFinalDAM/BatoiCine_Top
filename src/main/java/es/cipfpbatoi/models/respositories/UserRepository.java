package es.cipfpbatoi.models.respositories;

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

public class UserRepository {
    private UserDAO userDAO;

    public UserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public ArrayList<User> findAll() {
        return userDAO.findAll();
    }
    public void save(User user) {
        userDAO.save(user);
    }

    public boolean validUser(String name, String password) {
        return userDAO.validUser(name, password);
    }
    public User getById(int id) throws UserNotExistException{
        return userDAO.getById(id);
    }
    public int getLastCod(){
        return userDAO.getLastCod();

    }


}

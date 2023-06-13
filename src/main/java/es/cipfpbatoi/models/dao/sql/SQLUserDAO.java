package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.exception.UserAlreadyExistsException;
import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dao.UserDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.services.MySqlConnection;
import es.cipfpbatoi.utils.PasswordEncryptor;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class SQLUserDAO implements UserDAO {
    private Connection connection;

    public SQLUserDAO() {
        this.connection= new MySqlConnection().conectar();
    }

    private static final String TABLE_NAME = "Usuario";

    /**
     * Busca todos los usuarios del programa
     * @author Marcos Sanz
     * @return Una lista de todos los usuarios
     */

    @Override
    public ArrayList<User> findAll() {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);

        ArrayList<User> users = new ArrayList<>();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {

            while (resultSet.next()) {
                User user = getUserFromRegister(resultSet);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Crea un usuario a través de los campos string recibidos como parametro
     * @author Andreu Francés
     * @param resultSet
     * @return retorna un objeto usuario
     * @throws SQLException
     */

    private User getUserFromRegister(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String contrasenya = resultSet.getString("contraseña");;

        return new User(id, nombre, contrasenya);
    }

    /**
     * Guarda el usuario en la base de datos
     * @author Andreu Francés
     * @param user
     */

    @Override
    public void save(User user) {
        String sql = String.format("INSERT INTO %s (id, nombre, contraseña) VALUES (?,?,?)" , TABLE_NAME);
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.setString(3, user.getContrasenya() );
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recoge el ultimo código del usuario.
     * @author Marcos Sanz
     * @return el ultimo código del usuario
     */

    @Override
    public int getLastCod(){
        int lastId = 0;
        connection =  new MySqlConnection().conectar();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT MAX(id) AS maxId FROM Usuario");
            while (rs.next()) {
                int value = rs.getInt("maxId");
                lastId= value+1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;

    }

    /**
     * Recoge un usuario que coincide con el id pasado como parametro
     * @author Marcos Sanz
     * @param id
     * @return Un usuario
     * @throws UserNotExistException
     */

    @Override
    public User getById(int id) throws UserNotExistException {

        try (Statement statement = connection.createStatement()) {
            String sql="SELECT * FROM Usuario WHERE id=?";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int idUser = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String password = rs.getString("contraseña");
                return new User(idUser, nombre, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new UserNotExistException();
    }

    /**
     * Recoge el usuario que coincide con el nombre y la contraseña
     * @author Pablo Marin
     * @param name
     * @param password
     * @return Devuleve el usuario que coincide con el nombre y la contraseña
     * @throws UserNotExistException
     */

    @Override
    public User getUser(String name, String password) throws UserNotExistException {
        for (User user: findAll()) {
            if (user.getNombre().equals(name)) {
                if (validUser(name, password)) {
                    return user;
                }
            }
        }
        throw new UserNotExistException();
    }

    /**
     * Válida al usuario que coincide con la contraseña que tiene
     * @author Marcos Sanz
     * @param name
     * @param password
     * @return Un boolenano para saber si es correcto o no
     */

    @Override
    public boolean validUser(String name, String password) {

        String sql = "SELECT verificar_usuario(?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int result = resultSet.getInt(1);
                    return result == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


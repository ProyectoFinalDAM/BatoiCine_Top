package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.UserNotExistException;
import es.cipfpbatoi.models.dao.UserDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.services.MySqlConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class SQLUserDAO implements UserDAO {
    private Connection connection;

    public SQLUserDAO() {
        this.connection= new MySqlConnection().conectar();
    }

    private static final String TABLE_NAME = "Usuario";
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
    private User getUserFromRegister(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String contrasenya = resultSet.getString("contraseña");;

        return new User(id, nombre, contrasenya);
    }

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

    @Override
    public User getById(int id) throws UserNotExistException {

        try (Statement statement = connection.createStatement()) {
            String sql="SELECT * FROM Usuario WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
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

    @Override
    public boolean validUser(String name, String password) {
        for (User user: findAll()) {
            if (user.getNombre().equals(name)){
                if (BCrypt.checkpw(password, user.getContrasenya())) {
                    return true;
                }
            }
        }
        return false;
    }
}

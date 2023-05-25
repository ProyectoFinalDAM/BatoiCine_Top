package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.models.dao.UserDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class SQLUserDAO implements UserDAO {
    private Connection connection;

    private static final String TABLE_NAME = "BatoiCine_top";
    @Override
    public ArrayList<User> findAll() {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);

        ArrayList<User> users = new ArrayList<>();
        connection = new MySqlConnection().conectar();

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

        String id = resultSet.getString("id");
        String nombre = resultSet.getString("nombre");
        String apellidos = resultSet.getString("descripcion");
        String contrasenya = resultSet.getString("contraseña");;
        return new User(id, nombre, apellidos, contrasenya);
    }

    @Override
    public void save(User user) {
        String sql = String.format("INSERT INTO %s (id, nombre, descripcion, contraseña) VALUES (?,?,?,?)" ,
                TABLE_NAME);
        connection = new MySqlConnection().conectar();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.setString(3, user.getApellidos() );
            preparedStatement.setString(4, user.getContrasenya() );
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

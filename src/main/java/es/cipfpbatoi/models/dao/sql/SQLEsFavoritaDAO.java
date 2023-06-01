package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.EsFavoritaDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLEsFavoritaDAO implements EsFavoritaDAO {
    private Connection connection;

    public SQLEsFavoritaDAO() {
        connection = new MySqlConnection().conectar();
    }

    @Override
    public ArrayList<Produccion> getUserFavs(User user) throws DatabaseErrorException {
        ArrayList<Produccion> producciones = new ArrayList<>();
        String sql = String.format("SELECT * FROM Es_Favorita WHERE id_usuario=?");

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                producciones.add(new Produccion(resultSet.getString("id_produccion")));
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos" );
        }
        return producciones;
    }

    @Override
    public ArrayList<User> getProdUsers(Produccion produccion) throws DatabaseErrorException {
        ArrayList<User> usuarios = new ArrayList<>();
        String sql = String.format("SELECT * FROM Es_Favorita WHERE id_produccion=?");

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setString(1, produccion.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usuarios.add(new User(resultSet.getInt("id_usuario")));
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos" );
        }
        return usuarios;
    }

    @Override
    public void eliminar(User user, Produccion produccion) throws DatabaseErrorException {
        String sql = String.format("DELETE FROM %s WHERE id = ?", "Es_Favorita");
        connection =  new MySqlConnection().conectar();
        try (
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, produccion.getId());
            statement.setInt(2, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (delete)");
        }
    }

    @Override
    public void save(User user, Produccion produccion) {
        String sql = String.format("INSERT INTO Es_Favorita (id_produccion, id_usuario) VALUES (?,?)");
        connection = new MySqlConnection().conectar();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, produccion.getId());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

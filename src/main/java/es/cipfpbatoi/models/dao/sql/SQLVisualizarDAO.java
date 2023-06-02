package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.VisualizarDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Visualizar;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLVisualizarDAO implements VisualizarDAO {
    private Connection connection;

    public SQLVisualizarDAO() {
        this.connection = new MySqlConnection().conectar();;
    }

    @Override
    public ArrayList<User> getProdUsers(String id_produccion) throws DatabaseErrorException {
        ArrayList<User> usuarios = new ArrayList<>();
        String sql = String.format("SELECT * FROM Visualizar WHERE id_produccion=?");

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setString(1, id_produccion);
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
    public ArrayList<Produccion> getUserProducciones(int id_user) throws DatabaseErrorException {
        ArrayList<Produccion> produccions = new ArrayList<>();
        String sql = String.format("SELECT * FROM Visualizar WHERE id_usuario=?");

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setInt(1, id_user);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                produccions.add(new Produccion(resultSet.getString("id_produccion")));
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos" );
        }
        return produccions;
    }

    @Override
    public void save(Visualizar visualizar) throws DatabaseErrorException {
        String sql = String.format( "INSERT INTO Visualizar (id_produccion, id_usuario) VALUES (?,?)");

        for (Produccion prod: getUserProducciones(visualizar.getId_usuario())) {
            if (prod.getId().equals(visualizar.getId_produccion())){
                return;
            }
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setString( 1, visualizar.getId_produccion());
            preparedStatement.setInt( 2, visualizar.getId_usuario());
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos" );
        }
    }
}

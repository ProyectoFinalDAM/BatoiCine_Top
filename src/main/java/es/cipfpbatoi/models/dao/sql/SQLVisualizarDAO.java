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
    public ArrayList<User> getProdProducciones(Produccion produccion) throws DatabaseErrorException {
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
    public void save(Visualizar visualizar) throws DatabaseErrorException {

    }
}

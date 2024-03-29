package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.VisualizarDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Visualizar;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SQLVisualizarDAO implements VisualizarDAO {
    private Connection connection;

    public SQLVisualizarDAO() {
        this.connection = new MySqlConnection().conectar();;
    }

    /**
     * Busca en la base de datos las coincidencias de usuarios que han visto la pelicula pasada como parametro
     * @author Marcos Sanz
     * @param id_produccion
     * @return Retorna una lista de los usuarios que han visualiado la producción
     * @throws DatabaseErrorException
     */

    @Override
    public ArrayList<User> getProdUsers(String id_produccion) throws DatabaseErrorException {
        ArrayList<User> usuarios = new ArrayList<>();
        String sql = String.format("SELECT * FROM Visualizar WHERE id_produccion=? ORDER BY fecha DESC");

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
    public void sumarVisualizacion(String id_produccion) throws DatabaseErrorException {
        String sql = String.format("UPDATE Produccion SET visualizaciones=visualizaciones+1 WHERE id=?");

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setString(1, id_produccion);
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos" );
        }

    }

    /**
     * Busca en la base de datos las coincidencias de producciones que ha visto cierto usuario
     * @author Andreu Francés
     * @param id_user
     * @return Retorna una lista de las producciones que ha visualiado el usuario
     * @throws DatabaseErrorException
     */

    @Override
    public ArrayList<Produccion> getUserProducciones(int id_user) throws DatabaseErrorException {
        ArrayList<Produccion> produccions = new ArrayList<>();
        String sql = String.format("SELECT * FROM Visualizar WHERE id_usuario=? ORDER BY fecha DESC");

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

    /**
     * Guarda en la base de datos una visualizacion
     * @author Andreu Francés
     * @param visualizar
     * @throws DatabaseErrorException
     */

    @Override
    public void save(Visualizar visualizar) throws DatabaseErrorException {
        String sql = String.format( "INSERT INTO Visualizar (id_produccion, id_usuario, fecha) VALUES (?,?,?)");

        for (Produccion prod: getUserProducciones(visualizar.getId_usuario())) {
            if (prod.getId().equals(visualizar.getId_produccion())){
                return;
            }
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setString( 1, visualizar.getId_produccion());
            preparedStatement.setInt( 2, visualizar.getId_usuario());
            preparedStatement.setTimestamp( 3, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos" );
        }
    }
}

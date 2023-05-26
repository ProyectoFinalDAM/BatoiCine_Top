package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.ActorDAO;
import es.cipfpbatoi.models.dto.prods.Actor;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class SQLActorDAO implements ActorDAO {
    public static final String NOMBRE_TABLA = "Actor";
    private Connection connection;

    public SQLActorDAO() {
        this.connection= new MySqlConnection().conectar();
    }

    @Override
    public ArrayList<Actor> findAll() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s", NOMBRE_TABLA);
        ArrayList<Actor> actores = new ArrayList<>();

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Actor actor = getActorFromResultset(resultSet);
                actores.add(actor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en la conexión o acceso a la base de datos (select)");
        }

        return actores;
    }

    private Actor getActorFromResultset(ResultSet rs) throws SQLException {
        String id = rs.getString("id_actor");
        String nombre = rs.getString("nombre");

        return new Actor(id, nombre);
    }

    @Override
    public void save(Actor actor) throws DatabaseErrorException {
        String sql = String.format( "INSERT INTO %s (id_actor, nombre) VALUES (?,?)", NOMBRE_TABLA );

        try (PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )) {
            preparedStatement.setString( 1, actor.getId() );
            preparedStatement.setString( 2, actor.getNombre() );
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos (insert)" );
        }
    }
}

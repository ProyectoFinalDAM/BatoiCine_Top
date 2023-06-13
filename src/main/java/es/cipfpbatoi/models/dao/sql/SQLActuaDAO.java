package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.ActuaDAO;
import es.cipfpbatoi.models.dto.prods.Actua;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class SQLActuaDAO implements ActuaDAO {

    private static final String TABLE_NAME = "Actua";
    private Connection connection;

    /**
     * Busca todas las Actuaciones
     * @author Pablo Marin
     * @return Una lista de las actuaciones
     */

    @Override
    public ArrayList<Actua> findAll() {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);

        ArrayList<Actua> actoresEnPeliculas = new ArrayList<>();
        connection = new MySqlConnection().conectar();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {

            while (resultSet.next()) {
                Actua actua = getActuaFromRegister( resultSet);
                actoresEnPeliculas.add(actua);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actoresEnPeliculas;
    }

    /**
     * Convierte los campos string a un objeto Acuta
     * @author Pablo Marin
     * @param resultSet
     * @return retorna una actuación
     * @throws SQLException
     */

    private Actua getActuaFromRegister(ResultSet resultSet) throws SQLException {
        String id_actor = resultSet.getString("id_actor");
        String id_produccion = resultSet.getString("id_produccion");

        return new Actua(id_actor, id_produccion);
    }

    /**
     * Gurada una actuación
     * @author Pablo Marin
     * @param actua
     */

    @Override
    public void save(Actua actua) {
        String sql = String.format("INSERT INTO %s (id_actor, id_produccion) VALUES (?,?)" ,
                TABLE_NAME);
        connection = new MySqlConnection().conectar();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, actua.getIdActor());
            preparedStatement.setString(2, actua.getIdProduccion());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

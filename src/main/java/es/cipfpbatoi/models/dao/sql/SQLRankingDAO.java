package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dto.prods.Actor;
import es.cipfpbatoi.models.dto.prods.Ranking;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.util.ArrayList;

public class SQLRankingDAO implements RankingDAO {
    public static final String NOMBRE_TABLA = "Ranking";

    private Connection connection;

    /**
     * Busca todos los elementos del ranking
     * @author Andreu Francés
     * @return Una lista del resultado del ranking
     * @throws DatabaseErrorException
     */

    @Override
    public ArrayList<Ranking> findAll() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s", NOMBRE_TABLA);
        connection = new MySqlConnection().conectar();
        ArrayList<Ranking> rankings = new ArrayList<>();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {
                Ranking ranking = getActorFromResultset(resultSet);
                rankings.add(ranking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en la conexión o acceso a la base de datos (select)");
        }

        return rankings;
    }

    /**
     * Guarda en la base de datos todos los datos sobre el ranking
     * @author Andreu Francés
     * @param ranking
     * @throws DatabaseErrorException
     */

    @Override
    public void save(Ranking ranking) throws DatabaseErrorException {
        String sql = String.format( "INSERT INTO %s (id_produccion, puntos) VALUES (?,?)", NOMBRE_TABLA );
        connection = new MySqlConnection().conectar();

        try (
                PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )
        ) {
            preparedStatement.setString( 1, ranking.getId_produccion() );
            preparedStatement.setInt( 2, ranking.getPuntos() );
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos (insert)" );
        }
    }

    private Ranking getActorFromResultset(ResultSet rs) throws SQLException {
        String id_produccion = rs.getString("id_produccion");
        int puntos = rs.getInt("puntos");


        return new Ranking(id_produccion, puntos);
    }

}

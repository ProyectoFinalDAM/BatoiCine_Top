package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.GeneroDAO;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SQLGeneroDAO implements GeneroDAO {
    private Connection connection;

    private static final String TABLE_NAME = "Genero";
    @Override
    public ArrayList<Genero> findAll() {
        String sql = String.format("SELECT * FROM %s", TABLE_NAME);

        ArrayList<Genero> generos = new ArrayList<>();
        connection = new MySqlConnection().conectar();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {

            while (resultSet.next()) {
                Genero genero = getGeneroFromRegister(resultSet);
                generos.add(genero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return generos;
    }

    private Genero getGeneroFromRegister(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String cod = resultSet.getString("cod");
        String descripcion = resultSet.getString("descripcion");
        return new Genero(id, cod, descripcion);
    }

    @Override
    public void save(Genero genero) {
        String sql = String.format("INSERT INTO %s (id, cod, descripcion) VALUES (?,?,?)" ,
                TABLE_NAME);
        connection = new MySqlConnection().conectar();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, genero.getId());
            preparedStatement.setString(2, genero.getCod());
            preparedStatement.setString(3, genero.getDescripcion() );
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

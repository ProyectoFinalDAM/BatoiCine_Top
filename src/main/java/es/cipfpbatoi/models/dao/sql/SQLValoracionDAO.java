package es.cipfpbatoi.models.dao.sql;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.ValoracionDAO;
import es.cipfpbatoi.models.dto.User;
import es.cipfpbatoi.models.dto.prods.Valoracion;
import es.cipfpbatoi.models.services.MySqlConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class SQLValoracionDAO implements ValoracionDAO {

    public static final String NOMBRE_TABLA = "Valora";

    private Connection connection;

    public SQLValoracionDAO() {
        this.connection= new MySqlConnection().conectar();
    }

    @Override
    public ArrayList<Valoracion> findAll() throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s", NOMBRE_TABLA);

        ArrayList<Valoracion> valoracions = new ArrayList<>();

        try (
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {

            while (resultSet.next()) {
                Valoracion valoracion = geValoracionFromResultset(resultSet);
                valoracions.add(valoracion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en la conexión o acceso a la base de datos");
        }

        return valoracions;
    }


    @Override
    public boolean getById(Valoracion valoracion) throws NotFoundException, DatabaseErrorException {
        String sql = String.format("SELECT * FROM %s WHERE id_produccion = ? AND id_usuario = ?", NOMBRE_TABLA);


        try (
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, valoracion.getId_produccion());
            statement.setInt( 2, valoracion.getId_usuario());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Valoracion valoracion2 = getValoraFromRegister(resultSet);
                if (Objects.equals(valoracion2.getId_produccion(), valoracion.getId_produccion())&&valoracion2.getId_usuario()==valoracion.getId_usuario()) {
                    return true;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (select)");
        }
        return false;
    }
    private Valoracion getValoraFromRegister(ResultSet resultSet) throws SQLException {
        String idProduccion = resultSet.getString("id_produccion");
        int idUsuario = resultSet.getInt("id_usuario");
        int nota = resultSet.getInt("nota");
        String comentario = resultSet.getString("comentario");
        return new Valoracion(idProduccion, idUsuario, nota, comentario);
    }

    private void update(Valoracion valoracion) throws DatabaseErrorException {
        String sql = String.format("UPDATE %s SET nota = ?, comentario = ? WHERE id_produccion = ? AND id_usuario = ?",
                NOMBRE_TABLA);

        try (
                Connection connection = new MySqlConnection().conectar();
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            statement.setInt(1, valoracion.getNota());
            statement.setString(2, valoracion.getComentario());
            statement.setString(3, valoracion.getId_produccion());
            statement.setInt(4, valoracion.getId_usuario());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseErrorException("Ha ocurrido un error en el acceso o conexión a la base de datos (update)");
        }
    }

    private Valoracion geValoracionFromResultset(ResultSet rs) throws SQLException {
        String id_produccion = rs.getString("id_produccion");
        int id_usuario = rs.getInt("id_usuario");
        int nota = rs.getInt("nota");
        String comentario = rs.getString("comentario");

        return new Valoracion(id_produccion, id_usuario, nota, comentario);
    }

    @Override
    public void save(Valoracion valoracion) throws DatabaseErrorException, NotFoundException {
        if (getById(valoracion)) {
            update(valoracion);
        } else {
            insert(valoracion);
        }
    }

    private void insert(Valoracion valoracion) throws DatabaseErrorException {

        String sql = String.format( "INSERT INTO %s (id_produccion, id_usuario, nota, comentario) VALUES (?,?,?,?)", NOMBRE_TABLA );

        try (
                PreparedStatement preparedStatement = connection.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS )
        ) {
            preparedStatement.setString( 1, valoracion.getId_produccion());
            preparedStatement.setInt( 2, valoracion.getId_usuario());
            preparedStatement.setInt( 3, valoracion.getNota());
            preparedStatement.setString( 4, valoracion.getComentario());
            preparedStatement.executeUpdate();

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ha ocurrido un error en el acceso o conexión a la base de datos" );
        }
    }


}

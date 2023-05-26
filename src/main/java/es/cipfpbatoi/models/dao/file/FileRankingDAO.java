package es.cipfpbatoi.models.dao.file;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.RankingDAO;
import es.cipfpbatoi.models.dto.prods.Ranking;
import es.cipfpbatoi.models.dto.prods.Temporada;
import es.cipfpbatoi.utils.Validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRankingDAO implements RankingDAO {
    private static final int ID_PRODUCCION = 0;

    private static final int PUNTOS = 1;

    private static final String FILE_DATABASE = "resources/database/ranking.txt";

    @Override
    public ArrayList<Ranking> findAll() throws DatabaseErrorException {
        ArrayList<Ranking> rankings = new ArrayList<>();
        try {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_DATABASE))) {
                bufferedReader.readLine();
                do {
                    String register = bufferedReader.readLine();
                    if (register == null) {
                        return rankings;
                    }
                    rankings.add( getRankingFromRegister(register));
                } while (true);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return rankings;
    }
    private Ranking getRankingFromRegister(String register) {
        String[] fields = register.split( Validator.REGEX_TEMPORADAS );
        String id_produccion = fields[ID_PRODUCCION];
        int puntos = Integer.parseInt(fields[PUNTOS]);
        return new Ranking( id_produccion, puntos);
    }

    @Override
    public void save(Ranking ranking) throws DatabaseErrorException {

    }
}

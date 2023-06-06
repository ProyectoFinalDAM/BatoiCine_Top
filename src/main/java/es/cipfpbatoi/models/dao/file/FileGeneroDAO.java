package es.cipfpbatoi.models.dao.file;

import es.cipfpbatoi.models.dao.GeneroDAO;
import es.cipfpbatoi.models.dto.prods.Genero;

import java.io.*;
import java.util.ArrayList;

public class FileGeneroDAO implements GeneroDAO {
    private static final String FILE_DATABASE = "resources/database/generos.csv";

    private static final int ID = 0;

    private static final int COD = 1;

    private static final int DESCRIPCION = 2;

    private static final String FIELD_SEPARATOR = ",";
    private File file;
    public FileGeneroDAO() {
        this.file = new File( FILE_DATABASE );
    }


    @Override
    public void save(Genero genero) {

    }

    /**
     * Reune en una lista todos los géneros de la base de datos
     * @author Martin Peidro
     * @return ArrayList de género, devuelve todos los generos encontrados
     */

    @Override
    public ArrayList<Genero> findAll(){
        ArrayList<Genero> generos = new ArrayList<>();
        try {
            try (BufferedReader bufferedReader = getReader()) {
                bufferedReader.readLine();
                do {
                    String register = bufferedReader.readLine();
                    if (register == null) {
                        return generos;
                    }
                    generos.add(getGeneroFromRegister(register));
                } while (true);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return generos;
    }

    /**
     * Convierte los campos String a un objeto género
     * @author Martin Peidro
     * @param register
     * @return retorna una temporada
     */

    private Genero getGeneroFromRegister(String register) {
        String[] fields = register.split(FIELD_SEPARATOR);
        int id = Integer.parseInt(fields[ID]);
        String cod = fields[COD];
        String descripcion = fields[DESCRIPCION];
        return new Genero( id, cod, descripcion );
    }

    /**
     * Creas un buffered reader con el nombre del archivo dentro
     * @author Martín Peidro
     * @return BufferedReader
     * @throws IOException
     */

    private BufferedReader getReader() throws IOException {
        return new BufferedReader(new FileReader(file));
    }
}

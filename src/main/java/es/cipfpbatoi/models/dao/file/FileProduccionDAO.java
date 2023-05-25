package es.cipfpbatoi.models.dao.file;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.exception.NotFoundException;
import es.cipfpbatoi.models.dao.ProduccionDAO;
import es.cipfpbatoi.models.dto.prods.Calificacion;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.models.dto.prods.Tipo;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import java.util.ArrayList;

public class FileProduccionDAO implements ProduccionDAO {

    private static final String DATABASE_FILE = "resources/database/peliculas_series.csv";
    private final File file;
    public FileProduccionDAO() {
        this.file = new File(DATABASE_FILE);
    }
    @Override
    public ArrayList<Produccion> findAll() {
        ArrayList<Produccion> producciones = new ArrayList<>();

        try (FileReader fileReader = new FileReader(this.file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            do {
                String register = bufferedReader.readLine();
                if (register == null) {
                    return producciones;
                } else if (!register.isBlank()) {
                    String[] fields = register.split(";");
                    String id = fields[0];
                    String titulo = fields[1];
                    Calificacion calificacion;
                    if (fields[2].equals("PG-13")) {
                        calificacion = Calificacion.PG13;
                    } else {
                        calificacion = Calificacion.valueOf(fields[2]);
                    }
                    char[] caracteres = fields[3].toCharArray();
                    LocalDate fecha_lanzamiento;
                    if (caracteres.length == 8) {
                        caracteres[2] = Character.toUpperCase(caracteres[2]);
                        String fechaValida = new String(caracteres);
                        fecha_lanzamiento = getAnyoFormateado("0" + fechaValida);
                    } else if (caracteres.length == 9 || caracteres.length == 10) {
                        caracteres[3] = Character.toUpperCase(caracteres[3]);
                        String fechaValida = new String(caracteres);
                        fecha_lanzamiento = getAnyoFormateado(fechaValida);
                    } else {
                        String fechaValida = new String(caracteres);
                        fecha_lanzamiento = getAnyoFormateado(fechaValida);
                    }
                    String duracion = fields[4];
                    int mins;
                    if (duracion.equals("N/A")) {
                        mins = 0;
                    } else {
                        mins = Integer.parseInt(duracion.split(" ")[0]);
                    }
                    String genero = fields[5];
                    String director = fields[6];
                    String actores = fields[7];
                    String guion = fields[8];
                    String poster = fields[9];
                    Tipo tipo = null;
                    if (fields[10].equals("movie")) {
                        tipo = Tipo.MOVIE;
                    } else if (fields[10].equals("tv-show")) {
                        tipo = Tipo.TVSHOW;
                    }
                    String productora = fields[11];
                    String web = fields[12];
                    String plataforma = fields[13];


                    Set<String> actoresSet = new HashSet<>(Arrays.asList(actores.split(", ")));
                    Set<String> plataformas = new HashSet<>(Arrays.asList(plataforma.split(", ")));
                    Set<String> generos = new HashSet<>(Arrays.asList(genero.split(", ")));

                    // cambiar constructor
                    producciones.add(new Produccion(id, titulo, calificacion, fecha_lanzamiento, mins, generos, director, actoresSet, guion, productora, poster, plataformas, web, tipo));

                }
            } while (true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Produccion produccion) {
        // No se usa en el File
    }

    private LocalDate getAnyoFormateado(String fecha){

        DateTimeFormatter formato1 = DateTimeFormatter.ofPattern("dd-MMM-yy");
        DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd MMM yy");
        DateTimeFormatter formato3 = DateTimeFormatter.ofPattern("dd MMM yyyy");

        try {
            LocalDate fechaFormateada = LocalDate.parse(fecha, formato1);
            return fechaFormateada;
        } catch (DateTimeParseException e) {
            // No coincide con el formato dd-MMM-yy
        }

        try {
            LocalDate fechaFormateada = LocalDate.parse(fecha, formato2);
            return fechaFormateada;
        } catch (DateTimeParseException e) {
            // No coincide con el formato dd MMM yy
        }

        try {
            LocalDate fechaFormateada = LocalDate.parse(fecha, formato3);
            return fechaFormateada;
        } catch (DateTimeParseException e) {
            // No coincide con el formato dd MMM yyyy
        }

        return null;

    }

    @Override
    public Produccion getById(String dni) throws NotFoundException, DatabaseErrorException {
        return null;
    }
}


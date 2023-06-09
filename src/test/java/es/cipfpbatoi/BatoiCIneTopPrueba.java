package es.cipfpbatoi;

import es.cipfpbatoi.exception.DatabaseErrorException;
import es.cipfpbatoi.models.dao.sql.SQLGeneroDAO;
import es.cipfpbatoi.models.dao.sql.SQLProduccionDAO;
import es.cipfpbatoi.models.dto.prods.Genero;
import es.cipfpbatoi.models.dto.prods.Produccion;
import es.cipfpbatoi.utils.Validator;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BatoiCIneTopPrueba {

    public BatoiCIneTopPrueba() {
        System.out.println("-- Pruebas de la apliación --");
    }

    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll => setup(); Se ejecuta ANTES de empezar las pruebas");
    }

    @AfterAll
    static void tear(){
        System.out.println("@AfterAll => tear(); Se ejecuta al final de todas las pruebas");
    }

    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach => setupThis(); Se ejcuta ANTES de cada prueba");
    }

    @AfterEach
    void tearThis(){
        System.out.println("AfterEach => tearThis(); Se ejecuta DESPUÉS de cada prueba");
    }

    @Test
    void contrasenyaCorrecta(){
        boolean result1 = "@Abcd@123".matches(Validator.PASSWORD_REGEXP);
        assertTrue(result1);

        boolean result2 = "abc".matches(Validator.PASSWORD_REGEXP);
        assertFalse(result2);

        boolean result3 = "MiContraseña15".matches(Validator.PASSWORD_REGEXP);
        assertFalse(result3);

        boolean result4 = "12@".matches(Validator.PASSWORD_REGEXP);
        assertFalse(result4);
    }

    @Test
    void consultaProductora(){
        SQLProduccionDAO validator = new SQLProduccionDAO();
        Produccion produccion = validator.getCoincidenciaTitulo( "Argo" );
        String productora = "Warner Bros. Pictures";
        assertEquals( produccion.getProductora(), productora );

        assertFalse( produccion.getProductora().equals( "MiCasa" ) );
    }
}

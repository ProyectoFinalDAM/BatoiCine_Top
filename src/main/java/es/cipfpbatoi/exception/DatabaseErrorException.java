package es.cipfpbatoi.exception;

public class DatabaseErrorException extends Exception {

    /**
     * Muestra la excepción con el mensaje pasado como paramaetro
     * @author Pablo Marin
     * @param msg
     */
    public DatabaseErrorException(String msg) {
        super(msg);
    }
}

package es.cipfpbatoi.exception;

public class NotFoundException extends Exception{

    /**
     * Muestra la excepción con el mensaje pasado como paramaetro
     * @author Pablo Marin
     * @param mensaje
     */

    public NotFoundException(String mensaje) {
        super(mensaje);
    }
}

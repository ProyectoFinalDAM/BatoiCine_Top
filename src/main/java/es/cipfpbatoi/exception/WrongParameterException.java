package es.cipfpbatoi.exception;

public class WrongParameterException extends Exception{

    /**
     * Muestra la excepción con el mensaje pasado como paramaetro
     * @author Martin Peidro
     * @param message
     */

    public WrongParameterException(String message) {
        super(message);
    }
}

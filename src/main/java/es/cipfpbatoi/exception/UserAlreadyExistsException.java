package es.cipfpbatoi.exception;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super("El usuario ya existe.");
    }
}

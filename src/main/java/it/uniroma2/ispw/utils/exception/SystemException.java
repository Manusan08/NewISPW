package it.uniroma2.ispw.utils.exception;

public class SystemException extends Exception {
    public SystemException() {
        super("Si è presentato un errore tecnico, siamo spiacenti.");
    }

    public SystemException(String message) {
        super("errore tecnico.\n" + message);
    }
}

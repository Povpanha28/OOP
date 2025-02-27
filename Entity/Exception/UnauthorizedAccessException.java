package Entity.Exception;
public class UnauthorizedAccessException extends SecurityException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}

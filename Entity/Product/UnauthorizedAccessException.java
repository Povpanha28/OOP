package Entity.Product;

public class UnauthorizedAccessException extends Exception {
    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException(String message, String password) {
        
    }
}


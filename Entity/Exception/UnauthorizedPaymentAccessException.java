package Entity.Exception;

public class UnauthorizedPaymentAccessException extends Exception {
    public UnauthorizedPaymentAccessException(String message) {
        super(message);
    }
}

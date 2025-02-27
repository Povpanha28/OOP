package Entity.Exception;

public class InsufficientAmountException extends IllegalArgumentException {
    public InsufficientAmountException(String message) {
        super(message);
    }
}

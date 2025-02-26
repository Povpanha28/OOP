package Entity.Exception;

public class InvalidPaymentMethodException extends Exception{
    public InvalidPaymentMethodException(String message) {
        super(message);
    }
}

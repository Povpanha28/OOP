package Entity.Exception;

public class NegativeNumberException extends IllegalArgumentException {
    public NegativeNumberException(String message){
        super(message);
    }
}

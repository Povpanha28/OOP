package Entity.ExceptionSrc;
public class NegativeNumberException extends IllegalArgumentException {
    public NegativeNumberException(String message){
        super(message);
    }
    public NegativeNumberException(double input) throws NegativeNumberException{
        if (input < 0){
            throw new NegativeNumberException("Negative number is not allowed.");
        }
    }
}

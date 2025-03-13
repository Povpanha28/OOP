package Entity.ExceptionSrc;
public class InsufficientAmountException extends IllegalArgumentException {
    public InsufficientAmountException(String message) {
        super(message);
    }
    public InsufficientAmountException(double amountGiven, double amountPaid) throws InsufficientAmountException{
        if (amountGiven < amountPaid){
            throw new InsufficientAmountException("Insufficient amount.");
        }
    }
}

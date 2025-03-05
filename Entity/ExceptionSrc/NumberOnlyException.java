package Entity.ExceptionSrc;

public class NumberOnlyException extends IllegalArgumentException{
    public NumberOnlyException(String message){
        super(message);
    }

    public NumberOnlyException(String input, String format) throws NumberOnlyException{
        if (!input.matches(format)){
            throw new NumberOnlyException("Only numbers are allowed.");
        }
    }

}

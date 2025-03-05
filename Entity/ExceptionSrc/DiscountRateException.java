package Entity.ExceptionSrc;


public class DiscountRateException extends IllegalArgumentException{
    public DiscountRateException(String message){
        super(message);
    }
    public DiscountRateException(int number) throws DiscountRateException{
        if (number < 0 || number > 1){
            throw new IllegalArgumentException("Invalid discount. It must be between 0 and 100.");
        }
        else {
            System.out.println("Discount is valid.");
        }
    }
}

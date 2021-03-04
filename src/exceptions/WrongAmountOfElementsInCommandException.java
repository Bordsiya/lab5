package exceptions;

public class WrongAmountOfElementsInCommandException extends Exception{

    public WrongAmountOfElementsInCommandException(String message){
        super(message);
    }

}

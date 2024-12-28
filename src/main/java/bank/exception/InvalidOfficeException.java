package bank.exception;

public class InvalidOfficeException extends CreditRequestException {
    public InvalidOfficeException(String message) {
        super(message);
    }
}
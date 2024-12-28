package bank.exception;

public class InsufficientFundsException extends CreditRequestException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
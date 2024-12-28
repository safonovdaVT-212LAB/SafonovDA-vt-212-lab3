package bank.exception;

public class InvalidBankException extends CreditRequestException {
    public InvalidBankException(String message) {
        super(message);
    }
}
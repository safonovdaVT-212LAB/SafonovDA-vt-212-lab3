package bank.exception;

public class LowCreditRatingException extends CreditRequestException {
    public LowCreditRatingException(String message) {
        super(message);
    }
}
package bank.service;

import bank.entity.PaymentAccount;

import java.util.List;
import java.util.Optional;

public interface PaymentAccountService {
    PaymentAccount createPaymentAccount(PaymentAccount bank);

    List<PaymentAccount> getAllPaymentAccounts();

    Optional<PaymentAccount> getPaymentAccount(Long id);

    PaymentAccount updatePaymentAccount(Long id, PaymentAccount PaymentAccDetail);

    void deletePaymentAccount(Long id);

    void deleteAllPaymentAccounts();
}
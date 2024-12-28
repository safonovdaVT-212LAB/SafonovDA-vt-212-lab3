package bank.service.impl;

import bank.entity.*;
import bank.repository.*;
import bank.service.PaymentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static bank.util.Utils.getNullPropertyNames;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
public class PaymentAccountServiceImpl implements PaymentAccountService {
    public final PaymentAccountRepository paymentAccountRepository;
    public final BankUserRepository bankUserRepository;

    @Autowired
    public PaymentAccountServiceImpl(PaymentAccountRepository paymentAccountRepository, BankUserRepository bankUserRepository) {
        this.paymentAccountRepository = paymentAccountRepository;
        this.bankUserRepository = bankUserRepository;
    }

    public PaymentAccount createPaymentAccount(PaymentAccount paymentAccount) {
        paymentAccount.setCurrentAmount(0);

        PaymentAccount pa = paymentAccountRepository.save(paymentAccount);
        bankUserRepository.setPaymentAccountIdByUserId(pa.getId(), pa.getUserId());

        return pa;
    }

    public List<PaymentAccount> getAllPaymentAccounts() {
        return paymentAccountRepository.findAll();
    }

    public Optional<PaymentAccount> getPaymentAccount(Long id) {
        return paymentAccountRepository.findById(id);
    }

    public PaymentAccount updatePaymentAccount(Long id, PaymentAccount PaymentAccDetail) {
        Optional<PaymentAccount> PaymentAccount = paymentAccountRepository.findById(id);

        if (PaymentAccount.isPresent()) {
            PaymentAccount existingIntern = PaymentAccount.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(PaymentAccDetail, existingIntern, getNullPropertyNames(PaymentAccDetail));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(PaymentAccDetail.getId());
            return paymentAccountRepository.save(existingIntern);
        }

        return null;
    }

    public void deletePaymentAccount(Long id) {
        paymentAccountRepository.deleteById(id);
    }

    public void deleteAllPaymentAccounts() {
        paymentAccountRepository.deleteAll();
    }
}
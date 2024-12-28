package bank.service.impl;

import bank.entity.*;
import bank.repository.*;
import bank.service.CreditAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static bank.util.Utils.getNullPropertyNames;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
public class CreditAccountServiceImpl implements CreditAccountService {
    public final CreditAccountRepository creditAccountRepository;
    public final BankRepository bankRepository;
    public final BankUserRepository bankUserRepository;

    public CreditAccount createCreditAccount(CreditAccount creditAccount) {
        creditAccount.setInterestRate(bankRepository.getInterestRateByBankName(creditAccount.getBankName()));

        CreditAccount ca = creditAccountRepository.save(creditAccount);

        bankUserRepository.setCreditAccountIdByUserId(ca.getId(), ca.getUserId());

        return ca;
    }

    public List<CreditAccount> getAllCreditAccounts() { return creditAccountRepository.findAll(); }

    public Optional<CreditAccount> getCreditAccount(Long id) { return creditAccountRepository.findById(id); }

    public CreditAccount updateCreditAccount(Long id, CreditAccount creditAccDetail) {
        Optional<CreditAccount> CreditAccount = creditAccountRepository.findById(id);

        if (CreditAccount.isPresent()) {
            CreditAccount existingIntern = CreditAccount.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(creditAccDetail, existingIntern, getNullPropertyNames(creditAccDetail));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(creditAccDetail.getId());
            return creditAccountRepository.save(existingIntern);
        }

        return null;
    }

    public void deleteCreditAccount(Long id) { creditAccountRepository.deleteById(id); }

    public void deleteAllCreditAccounts() { creditAccountRepository.deleteAll(); }
}
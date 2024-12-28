package bank.service;

import bank.entity.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
    Bank createBank(Bank bank);

    List<Bank> getAllBanks();

    Optional<Bank> getBank(Long id);

    Bank updateBank(Long id, Bank bankDetails);

    void deleteBank(Long id);

    void deleteAllBanks();

    void outputAllBankInfo(Long id);
}
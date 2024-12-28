package bank.service;

import bank.entity.BankOffice;

import java.util.List;
import java.util.Optional;

public interface BankOfficeService {
    BankOffice createBankOffice(BankOffice bank);

    List<BankOffice> getAllBankOffices();

    Optional<BankOffice> getBankOffice(Long id);

    BankOffice updateBankOffice(Long id, BankOffice officeDetail);

    void deleteBankOffice(Long id);

    void deleteAllBanksOffices();
}
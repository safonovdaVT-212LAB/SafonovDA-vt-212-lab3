package bank.service.impl;

import bank.entity.BankAtm;
import bank.repository.*;
import bank.service.BankAtmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static bank.util.Utils.getNullPropertyNames;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
public class BankAtmServiceImpl implements BankAtmService {
    public final BankAtmRepository bankAtmRepository;
    public final BankRepository bankRepository;
    public final BankOfficeRepository bankOfficeRepository;

    public BankAtm createBankAtm(BankAtm atm) {
        Long bankId = atm.getBankId();
        Long bankOfficeId = atm.getBankOfficeId();

        atm.setTotalMoney(bankRepository.getTotalMoneyByBankId(bankId));
        atm.setAddress(bankOfficeRepository.getAddressByOfficeId(bankOfficeId));

        bankRepository.incrementNumberAtmsByBankId(bankId);
        bankOfficeRepository.incrementNumberAtmsByOfficeId(bankOfficeId);

        return bankAtmRepository.save(atm);
    }

    public List<BankAtm> getAllBankAtms() {
        return bankAtmRepository.findAll();
    }

    public Optional<BankAtm> getBankAtm(Long id) {
        return bankAtmRepository.findById(id);
    }

    public BankAtm updateBankAtm(Long id, BankAtm atmDetail) {
        Optional<BankAtm> bankAtm = bankAtmRepository.findById(id);

        if (bankAtm.isPresent()) {
            BankAtm existingIntern = bankAtm.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(atmDetail, existingIntern, getNullPropertyNames(atmDetail));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(atmDetail.getId());
            return bankAtmRepository.save(existingIntern);
        }

        return null;
    }

    public void deleteBankAtm(Long id) {
        bankAtmRepository.deleteById(id);
    }

    public void deleteAllBanksAtms() {
        bankAtmRepository.deleteAll();
    }
}

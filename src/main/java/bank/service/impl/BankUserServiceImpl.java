package bank.service.impl;

import bank.entity.BankUser;
import bank.entity.*;
import bank.repository.*;
import bank.service.BankUserService;
import bank.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static bank.util.Utils.getNullPropertyNames;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
public class BankUserServiceImpl implements BankUserService {
    public final BankUserRepository userRepository;
    public final BankRepository bankRepository;
    public final CreditAccountRepository creditAccountRepository;
    public final PaymentAccountRepository paymentAccountRepository;

    @Override
    public BankUser createUser(BankUser user) {
        int monthlyIncome = Utils.getRandomFromAToB(0, 10000);
        user.setMonthlyIncome(monthlyIncome);

        int b = monthlyIncome <= 1000 ? 100 : (int) Math.ceil((double) monthlyIncome / 1000) * 100;
        int a = b - 100;
        user.setCreditRating(Utils.getRandomFromAToB(a, b));

        bankRepository.incrementNumberUsersByBankName(user.getBankUsed());

        return userRepository.save(user);
    }

    @Override
    public List<BankUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<BankUser> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public BankUser updateUser(Long id, BankUser bankUserDetail) {
        Optional<BankUser> User = userRepository.findById(id);

        if (User.isPresent()) {
            BankUser existingIntern = User.get();
            String[] ignore = getNullPropertyNames(existingIntern);
            copyProperties(bankUserDetail, existingIntern, getNullPropertyNames(bankUserDetail));
            System.out.println(Arrays.toString(ignore));

            existingIntern.setId(bankUserDetail.getId());
            return userRepository.save(existingIntern);
        }

        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public void outputAllUserInfo(Long id) {
        Optional<BankUser> user = getUser(id);
        if (user.isPresent()) {
            System.out.println("Bank User:");
            System.out.println("\t" + user.get());

            List<CreditAccount> creditAccounts = creditAccountRepository.selectByUserId(id);
            List<PaymentAccount> paymentAccounts = paymentAccountRepository.selectByUserId(id);

            System.out.println("Credit Account:");
            for (CreditAccount creditAccount : creditAccounts) {
                System.out.println("\t" + creditAccount);
            }
            System.out.println("Payment Account:");
            for (PaymentAccount paymentAccount : paymentAccounts) {
                System.out.println("\t" + paymentAccount);
            }
        } else {
            System.out.println("NOT-FOUND");
        }
    }
}
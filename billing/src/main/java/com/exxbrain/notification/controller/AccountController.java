package com.exxbrain.notification.controller;

import com.exxbrain.notification.ApplicationException;
import com.exxbrain.notification.entity.Account;
import com.exxbrain.notification.entity.AccountRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    private final AccountRepository repository;

    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    private Account getAccount(String userId) {
        return repository.findById(userId).orElseGet(() -> {
            var newAccount = new Account();
            newAccount.setUserId(userId);
            newAccount.setBalance(new BigDecimal(0));
            return newAccount;
        });
    }

    @PostMapping("/accounts/{id}/addMoney")
    public Account addMoney(@PathVariable String id, @RequestBody Money money) {
        var account = getAccount(id);
        account.setBalance(account.getBalance().add(money.getAmount()));
        repository.save(account);
        return account;
    }

    @PostMapping("/accounts/{id}/subtractMoney")
    public Account subtractMoney(@PathVariable String id, @RequestBody Money money) {
        var account = getAccount(id);
        var resultBalance = account.getBalance().subtract(money.getAmount());
        if (resultBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new ApplicationException("Not enough money");
        }
        account.setBalance(account.getBalance().subtract(money.getAmount()));
        repository.save(account);
        return account;
    }

}

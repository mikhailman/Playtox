package com.verishko.playtoxtask.controller;

import com.verishko.playtoxtask.exception.MoneyException;
import com.verishko.playtoxtask.repository.Account;
import com.verishko.playtoxtask.service.AccountService;

import java.util.List;

public class AccountController implements Controller {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    public void addAccount(int money) {
        this.service.createNewAccount(money);
    }

    public Account getRandomAccount() {
        return this.service.getRandomAccount();
    }

    @Override
    public List<Account> getAllAccounts() {
        return this.service.getAll();
    }

    @Override
    public String transaction(Account accountFrom, Account accountTo, int sum) {
        try {
            service.transaction(accountFrom, accountTo, sum);
            return String.format(
                    "the transfer from account=%s to account=%s in the sum of=%s$ was completed successfully.",
                    accountFrom,
                    accountTo,
                    sum);
        } catch (MoneyException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Internal error. Transfer was unsuccessfully.";
        }
    }
}

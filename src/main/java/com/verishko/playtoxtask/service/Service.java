package com.verishko.playtoxtask.service;

import com.verishko.playtoxtask.repository.Account;
import com.verishko.playtoxtask.dto.AccountDTO;

import java.util.List;

public interface Service {

    void createNewAccount(int money);

    AccountDTO transaction(Account accountFrom, Account accountTo, int sum);

    Account getRandomAccount();

    List<Account> getAll();
}

package com.verishko.playtoxtask.controller;

import com.verishko.playtoxtask.repository.Account;

import java.util.List;

public interface Controller {

    void addAccount(int money);

    Account getRandomAccount();

    List<Account> getAllAccounts();

    String transaction(Account accountFrom, Account accountTo, int sum);
}

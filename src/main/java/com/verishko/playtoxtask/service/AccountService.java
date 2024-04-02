package com.verishko.playtoxtask.service;

import com.verishko.playtoxtask.repository.Account;
import com.verishko.playtoxtask.repository.AccountRepository;
import com.verishko.playtoxtask.dto.AccountDTO;
import com.verishko.playtoxtask.exception.MoneyException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccountService implements Service {

    private final static Random RANDOM = new Random();

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public void createNewAccount(int money) {
        Account account = new Account(UUID.randomUUID().toString(), money);
        this.repository.addToRepository(account);
    }

    public AccountDTO transaction(Account accountFrom, Account accountTo, int sum) {
        LocalDateTime start = LocalDateTime.now();
        ReentrantReadWriteLock.WriteLock writeLock = accountFrom.writeLock();
        writeLock.lock();
        try {
            if (accountFrom.getMoneySum() < sum) {
                throw new MoneyException(String.format("Account with id = %s are not enough funds to write off the sum = %s", accountFrom.getId(), sum));
            } else {
                int fromCurrentBalance = accountFrom.subtractSum(sum);
                writeLock.unlock();

                writeLock = accountTo.writeLock();
                writeLock.lock();

                int toCurrentBalance = accountTo.addSum(sum);
                writeLock.unlock();
                LocalDateTime finished = LocalDateTime.now();

                return new AccountDTO(accountFrom.getId(), fromCurrentBalance, accountTo.getId(), toCurrentBalance, start, finished);
            }
        } finally {
            if (writeLock.isHeldByCurrentThread()) {
                writeLock.unlock();
            }
        }
    }

    public Account getRandomAccount() {
        List<Account> accounts = this.getAll();
        return accounts.get(RANDOM.nextInt(accounts.size()));
    }

    public List<Account> getAll() {
        return this.repository.getAllAccounts();
    }
}

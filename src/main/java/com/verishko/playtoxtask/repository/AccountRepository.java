package com.verishko.playtoxtask.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountRepository implements Repository {

    private final Map<String, Account> repository = new ConcurrentHashMap<>();

    public void addToRepository(Account account) {
        this.repository.put(account.getId(), account);
    }

    public Account getAccountById(String id) {
        return repository.get(id);
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(this.repository.values());
    }
}

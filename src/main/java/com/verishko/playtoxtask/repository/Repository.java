package com.verishko.playtoxtask.repository;

import java.util.List;

public interface Repository {

    void addToRepository(Account account);

    Account getAccountById(String id);

    List<Account> getAllAccounts();

}

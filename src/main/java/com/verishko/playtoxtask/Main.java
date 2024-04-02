package com.verishko.playtoxtask;

import com.verishko.playtoxtask.controller.AccountController;
import com.verishko.playtoxtask.repository.Account;
import com.verishko.playtoxtask.repository.AccountRepository;
import com.verishko.playtoxtask.service.AccountService;
import com.verishko.playtoxtask.service.LoggerService;
import com.verishko.playtoxtask.util.RandomTransfer;

public class Main {
    public static void main(String[] args) {

        int accountCount = 4;
        int accountMoney = 10000;
        int threadsCount = 5;
        int minSleepTime = 1000;
        int maxSleepTime = 2000;
        int transactions = 30;

        AccountRepository repository = new AccountRepository();
        AccountService service = new LoggerService(repository);
        AccountController controller = new AccountController(service);

        for (int i = 0; i < accountCount; i++) {
            controller.addAccount(accountMoney);
        }

        int sumMoneyBefore = controller.getAllAccounts().stream().mapToInt(Account::getMoneySum).sum();

        RandomTransfer randomTransfer = new RandomTransfer(controller, accountMoney);
        randomTransfer.run(transactions, threadsCount, minSleepTime, maxSleepTime);

        int sumMoneyAfter = controller.getAllAccounts().stream().mapToInt(Account::getMoneySum).sum();

        System.out.printf("sumBalanceBefore = %s ; sumBalanceAfter = %s; diff = %s\n", sumMoneyBefore, sumMoneyAfter, sumMoneyBefore - sumMoneyAfter);
    }
}

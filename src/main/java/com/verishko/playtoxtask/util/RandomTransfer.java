package com.verishko.playtoxtask.util;

import com.verishko.playtoxtask.controller.AccountController;
import com.verishko.playtoxtask.repository.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomTransfer {

    public static final Logger LOGGER = LogManager.getLogger(RandomTransfer.class.getSimpleName());

    private final AccountController accountController;

    private final int startMoney;

    public RandomTransfer(AccountController accountController, int startMoney) {
        LOGGER.trace("create");
        this.accountController = accountController;
        this.startMoney = startMoney;
    }

    public void run(int repeats, int threadsCount, int minSleepTime, int maxSleepTime) {
        LOGGER.trace("execute run");
        AtomicInteger maxTransactions = new AtomicInteger(repeats);
        Thread[] threads = new Thread[threadsCount];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                while (maxTransactions.decrementAndGet() >= 0) {
                    try {
                        Thread.sleep((long) (minSleepTime + (Math.random() * maxSleepTime)));
                        Account from = accountController.getRandomAccount();
                        Account to;
                        do {
                            to = accountController.getRandomAccount();
                        } while (Objects.equals(from, to));
                        System.out.println(accountController.transaction(from, to, (int) (Math.random() * startMoney)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            LOGGER.trace("Thread - {} created", i + 1);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            LOGGER.trace("Thread - {} finished", i + 1);
        }
        LOGGER.trace("all thread finished");
    }
}

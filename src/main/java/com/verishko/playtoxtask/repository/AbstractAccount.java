package com.verishko.playtoxtask.repository;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class AbstractAccount extends ReentrantReadWriteLock {

    public abstract int addSum(int moneySum);

    public abstract int subtractSum(int moneySum);

}

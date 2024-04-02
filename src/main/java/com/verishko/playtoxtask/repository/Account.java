package com.verishko.playtoxtask.repository;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Account extends AbstractAccount {

    // random default
//    private final String id = UUID.randomUUID().toString();
    private String id;

    // 10000 default
//    private int moneySum = 10000;
    private int moneySum;

    public int addSum(int moneySum) {
        return this.moneySum += moneySum;
    }

    public int subtractSum(int moneySum) {
        return this.moneySum -= moneySum;
    }
}



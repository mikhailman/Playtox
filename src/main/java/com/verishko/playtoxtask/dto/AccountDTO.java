package com.verishko.playtoxtask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AccountDTO {

    private final String from;

    private final int fromCurrentBalance;

    private final String to;

    private final int toCurrentBalance;

    private final LocalDateTime started;

    private final LocalDateTime finished;

}

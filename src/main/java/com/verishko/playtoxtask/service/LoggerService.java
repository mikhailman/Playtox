package com.verishko.playtoxtask.service;

import com.verishko.playtoxtask.repository.Account;
import com.verishko.playtoxtask.repository.AccountRepository;
import com.verishko.playtoxtask.dto.AccountDTO;
import com.verishko.playtoxtask.exception.MoneyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerService extends AccountService {
    static final Logger LOGGER = LogManager.getLogger();

    public LoggerService(AccountRepository repository) {
        super(repository);
    }

    @Override
    public AccountDTO transaction(Account accountFrom, Account accountTo, int sum) {
        try {
            AccountDTO accountDTO = super.transaction(accountFrom, accountTo, sum);
            LOGGER.info("""
                    The transfer from account={} to account={} in the sum of={}$ was completed successfully.
                    Account {} has {}$ on the balance;
                    Account {} has {}$ on the balance;
                    """, accountFrom.getId(), accountTo.getId(), sum, accountDTO.getFrom(), accountDTO.getFromCurrentBalance(), accountDTO.getTo(), accountDTO.getToCurrentBalance());
            return accountDTO;
        } catch (MoneyException e) {
            LOGGER.info("The transfer from account={} to account={} in the sum of={}$ was completed unsuccessfully.", accountFrom.getId(), accountTo.getId(), sum);
            LOGGER.warn(e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }
}

package com.n26.service;

import com.n26.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@Slf4j
public class TransactionServiceImplTest {

    TransactionService service = new TransactionServiceImpl();

    @Test
    public void whenAddTransactionOlderThan60Secs_thenTransactionIsIgnored() {
        log.info("Test: transactions older than 60 secs must be ignored");

        Instant before60Seconds = Instant.now().minusSeconds(61t );
        Transaction oldTransaction = new Transaction(new BigDecimal(123.45), before60Seconds);
        assertFalse(service.addTransaction(oldTransaction));
        assertEquals(0, service.getTransactions().size());
    }

    // 2. whenTransactionsGetOlderThan60Sec_thenTheyMustBeDeleted


}

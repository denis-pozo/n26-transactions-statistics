package com.n26.service;

import com.n26.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.*;

@Slf4j
public class TransactionServiceImplTest {

    private final TransactionService service = new TransactionServiceImpl();

    @Test
    public void whenAddTransactionOlderThan60Secs_thenTransactionIsIgnored() {
        log.info("Test: transactions older than 60 sec must be ignored");

        Instant before60Seconds = Instant.now().minusSeconds(61);
        Transaction transaction = new Transaction(new BigDecimal(123.456), before60Seconds);

        assertFalse(service.addTransaction(transaction));
        assertEquals(0, service.getTransactions().size());
    }

    @Test
    public void whenTransactionGetsObsolete_thenDeleteThem() throws InterruptedException {
        log.info("Test: stored transactions older than 60 sec must be removed");

        Instant before58 = Instant.now().minusSeconds(58);
        Transaction transaction = new Transaction(new BigDecimal(123.456), before58);

        assertTrue(service.addTransaction(transaction));
        assertEquals(1, service.getTransactions().size());

        Thread.sleep(3000);
        assertEquals(0, service.getTransactions().size());
    }
}

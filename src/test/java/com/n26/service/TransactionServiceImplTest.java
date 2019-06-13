package com.n26.service;

import com.n26.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.*;

@Slf4j
public class TransactionServiceImplTest {

    private final TransactionServiceImpl service = new TransactionServiceImpl();
    private final static BigDecimal AMOUNT = BigDecimal.valueOf(123.4567);

    @Test
    public void whenAddTransactionOlderThan60Secs_thenTransactionIsIgnored() {
        log.info("Test: transactions older than 60 sec must be ignored");

        Instant before60Seconds = Instant.now().minusSeconds(61);
        Transaction transaction = new Transaction(AMOUNT, before60Seconds);

        assertFalse(service.addTransaction(transaction));
        assertEquals(0, service.getTransactions().size());
    }

    @Test
    public void whenTransactionGetsObsolete_thenDeleteThem() throws InterruptedException {
        log.info("Test: stored transactions older than 60 sec must be removed");

        Instant before50 = Instant.now().minusSeconds(50);
        Instant before58 = Instant.now().minusSeconds(58);
        Transaction transactionA = new Transaction(AMOUNT, before58);
        Transaction transactionB = new Transaction(AMOUNT, before50);

        assertTrue(service.addTransaction(transactionA));
        assertEquals(1, service.getTransactions().size());
        assertTrue(service.addTransaction(transactionB));
        assertEquals(2, service.getTransactions().size());

        Thread.sleep(3000);
        assertEquals(1, service.getTransactions().size());

        Thread.sleep(7000);
        assertEquals(0, service.getTransactions().size());
    }

    @Test
    public void whenTransactionsAreAddedWithoutOrder_thenUpdateMustWork() throws InterruptedException {
        log.info("Test: unordered stored transactions does not affect the update");

        Instant before50 = Instant.now().minusSeconds(50);
        Transaction transaction58secsOld = new Transaction(AMOUNT, before50);
        service.addTransaction(transaction58secsOld);

        Instant before10 = Instant.now().minusSeconds(10);
        Transaction transaction10secsOld = new Transaction(AMOUNT, before10);
        service.addTransaction(transaction10secsOld);

        Instant before30 = Instant.now().minusSeconds(30);
        Transaction transaction30secsOld = new Transaction(AMOUNT, before30);
        service.addTransaction(transaction30secsOld);

        assertEquals(3, service.getTransactions().size());

        Thread.sleep(15000);
        assertEquals(2, service.getTransactions().size());
    }

}

package com.n26.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class TransactionTest {

    private static final BigDecimal amount = BigDecimal.valueOf(12.3347);
    private static final Instant timestamp = Instant.now();

    @Test
    public void constructorShouldCreateAnInstanceCorrectly() {
        log.info("Test: constructor creates an instance when input parameters are correct");
        Transaction transaction = new Transaction( amount, timestamp);
        assertNotNull(transaction);
    }

    @Test(expected = NullPointerException.class)
    public void constructorShouldThrowExceptionWhen_AmountIsNull() throws Exception {
        log.info("Test: constructor throws exception when input argument 'amount' is null");
        Transaction transaction = new Transaction(null, timestamp);
    }

    @Test(expected = Exception.class)
    public void constructorShouldThrowExceptionWhen_TimestampIsNull() throws Exception {
        log.info("Test: constructor throws exception when input argument 'timestamp' is null");
        Transaction transaction = new Transaction(amount, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionWhen_TimestampIsInTheFuture() {
        log.info("Test: constructor throws exception when input argument 'timestamp' is in the future");
        Instant futureTimestamp = Instant.now().plusMillis(50);
        Transaction transaction = new Transaction(amount, futureTimestamp);
    }

    @Test
    public void whenAmountIsInt_thenConstructorShouldCreateTransaction() {
        log.info("Test: constructor creates transaction when amount is an integer, too");
        Transaction transaction = new Transaction(BigDecimal.valueOf(3), timestamp);
        assertNotNull(transaction);
    }
}

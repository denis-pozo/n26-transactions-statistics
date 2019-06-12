package com.n26.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
        Instant timestamp = Instant.now().plusMillis(50);
        Transaction transaction = new Transaction(amount, timestamp);
    }
}

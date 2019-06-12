package com.n26.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;

@Value
public class Transaction {

    BigDecimal amount;
    Instant timestamp;

    public Transaction(final BigDecimal amount, final Instant timestamp){
        if(amount == null || timestamp == null) {
            throw new NullPointerException("Input parameters cannot be null");
        }

        /* Assumption: No delay from the POST request with the transaction until the creation of
         * the instance. Theoretically a faked post request with a date in the future (~ delay)
         * will pass this argument checker. However, at this level is almost impossible to correct
         * the effect of the delay. */
        if (timestamp.isAfter(Instant.now())) {
            throw new IllegalArgumentException (
                "Illegal timestamp: Transactions cannot be done in the future (yet)");
        }

        this.amount = amount;
        this.timestamp = timestamp;
    }

}

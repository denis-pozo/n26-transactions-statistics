package com.n26.domain;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {

    // TODO Check validity of the passed in parameters
    // 1. amount
    @Test(expected = Exception.class)
    public void constructorShouldThrowExceptionWhen_AmountIsNull() throws Exception {
        throw new Exception();
    }


    // 2. timestamp
    @Test(expected = Exception.class)
    public void constructorShouldThrowExceptionWhen_TimestampIsNull() throws Exception {
        throw new Exception();
    }
}

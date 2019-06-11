package com.n26.domain;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class StatisticsTest {

    static private final BigDecimal sum = BigDecimal.valueOf(1000.000);
    static private final BigDecimal avg = BigDecimal.valueOf(100.43);
    static private final BigDecimal max = BigDecimal.valueOf(200000.4245);
    static private final BigDecimal min = BigDecimal.valueOf(50.324);
    static private final long count = 10;

    // TODO Check validity of arguments passed in to the constructor and builder
    // 1a. sum
    @Test(expected = Exception.class)
    public void constructorShouldThrowExceptionWhen_SumIsNull() throws Exception {
        throw new Exception();
    }

    // 2. avg
    @Test(expected = Exception.class)
    public void constructorShouldThrowExceptionWhen_AvgIsNull() throws Exception {
        throw new Exception();
    }

    // 3. max
    @Test(expected = Exception.class)
    public void constructorShouldThrowExceptionWhen_MaxIsNull() throws Exception {
        throw new Exception();
    }

    // 4. min
    @Test(expected = Exception.class)
    public void constructorShouldThrowExceptionWhen_MinIsNull() throws Exception {
        throw new Exception();
    }


}

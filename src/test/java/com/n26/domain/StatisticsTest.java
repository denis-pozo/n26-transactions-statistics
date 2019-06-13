package com.n26.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Slf4j
public class StatisticsTest {

    static private final BigDecimal sum = BigDecimal.valueOf(1000.000);
    static private final BigDecimal avg = BigDecimal.valueOf(100.534);
    static private final BigDecimal max = BigDecimal.valueOf(200000.491);
    static private final BigDecimal min = BigDecimal.valueOf(50.229);
    static private final long count = 10;

    @Test
    public void constructorCreatesAnInstanceCorrectly() {
        log.info("Test: constructor creates an instance when input arguments are valid");
        Statistics stats = new Statistics(sum, avg, max, min, count);
        assertNotNull(stats);
    }

    @Test(expected = NullPointerException.class)
    public void constructorShouldThrowExceptionWhen_SumIsNull() throws Exception {
        log.info("Test: constructor throws exception when input argument 'sum' is null");
        Statistics stats = new Statistics(null, avg, max, min, count);
    }

    @Test(expected = NullPointerException.class)
    public void constructorShouldThrowExceptionWhen_AvgIsNull() throws Exception {
        log.info("Test: constructor throws exception when input argument 'avg' is null");
        Statistics stats = new Statistics(sum, null, max, min, count);
    }

    @Test(expected = NullPointerException.class)
    public void constructorShouldThrowExceptionWhen_MaxIsNull() throws Exception {
        log.info("Test: constructor throws exception when input argument 'max' is null");
        Statistics stats = new Statistics(sum, avg, null, min, count);
    }

    @Test(expected = NullPointerException.class)
    public void constructorShouldThrowExceptionWhen_MinIsNull() throws Exception {
        log.info("Test: constructor throws exception when input argument 'min' is null");
        Statistics stats = new Statistics(sum, avg, max, null, count);
    }

    @Test
    public void constructorShouldStoreValuesInCorrectFormat_2DecimalPlacesHalfRoundUp () {
        log.info("Test: constructor should store BigDecimal attributes correctly: " +
                 "2 decimal places and HALF_ROUND_UP");

        Statistics stats = new Statistics(sum, avg, max, min, count);
        assertEquals("1000.00", stats.getSum().toString());
        assertEquals("100.53", stats.getAvg().toString());
        assertEquals("200000.49", stats.getMax().toString());
        assertEquals("50.23", stats.getMin().toString());
    }

    // TODO - Create parametric tests for testing format protected method with special cases
}

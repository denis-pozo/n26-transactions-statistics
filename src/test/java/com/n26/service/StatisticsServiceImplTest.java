package com.n26.service;

import com.n26.domain.StatisticsResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@Slf4j
public class StatisticsServiceImplTest {

    private static StatisticsService service = new StatisticsServiceImpl();

    @Test
    public void whenTransactionsListIsEmpty_thenReturnZeroedStatistics() {
        log.info("Test: return zeroed statistics if empty transactions list ");

        StatisticsResponse response = service.getStatistics(new ArrayList<>());
        assertEquals("0.00", response.getSum());
        assertEquals("0.00", response.getAvg());
        assertEquals("0.00", response.getMax());
        assertEquals("0.00", response.getMin());
        assertEquals(0, response.getCount());
    }

}

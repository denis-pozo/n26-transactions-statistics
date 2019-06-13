package com.n26.service;

import com.n26.domain.Statistics;
import com.n26.domain.Transaction;
import org.apache.commons.lang3.concurrent.BackgroundInitializer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public Statistics computeStatistics(List<Transaction> transactions) {
        List<BigDecimal> amounts = transactions.stream()
                                    .map(Transaction::getAmount)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toList());

        BigDecimal sum = amounts.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avg = computeAvg(amounts);
        BigDecimal min = computeMin(amounts);
        BigDecimal max = computeMax(amounts);

        return new Statistics(sum, avg, max, min, amounts.size());
    }

    private BigDecimal computeAvg(List<BigDecimal> amounts) {
        BigDecimal avg = BigDecimal.ZERO;

        for(BigDecimal number : amounts) {
            if(number != null) {
                avg = avg.add(number);
            }
        }

        return avg.divide(new BigDecimal(amounts.size()));
    }

    private BigDecimal computeMax(List<BigDecimal> amounts) {
        BigDecimal max = BigDecimal.ZERO;

        for(BigDecimal number : amounts) {
            if(max.compareTo(number) < 0) {
                max = number;
            }
        }

        return max;
    }

    private BigDecimal computeMin(List<BigDecimal> amounts) {
        BigDecimal min = BigDecimal.ZERO;

        for(BigDecimal number : amounts) {
            if(min.compareTo(number) > 0) {
                min = number;
            }
        }
        return min;
    }

}

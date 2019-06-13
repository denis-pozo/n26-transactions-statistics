package com.n26.service;

import com.n26.domain.Statistics;
import com.n26.domain.StatisticsResponse;
import com.n26.domain.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public StatisticsResponse getStatistics(List<Transaction> transactions) {
        Statistics statistics = computeStatistics(transactions);

        return new StatisticsResponse(
                statistics.getSum().toString(),
                statistics.getAvg().toString(),
                statistics.getMax().toString(),
                statistics.getMin().toString(),
                statistics.getCount()
        );
    }

    private Statistics computeStatistics(List<Transaction> transactions) {
        if(transactions.size() == 0) {
            return new Statistics(BigDecimal.ZERO,
                                  BigDecimal.ZERO,
                                  BigDecimal.ZERO,
                                  BigDecimal.ZERO,
                            0);
        }

        List<BigDecimal> amounts = transactions.stream()
                                    .map(Transaction::getAmount)
                                    .filter(Objects::nonNull)
                                    .collect(Collectors.toList());

        BigDecimal sum = amounts.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avg = computeAvg(amounts);
        BigDecimal max = computeMax(amounts);
        BigDecimal min = computeMin(amounts);

        return new Statistics(sum, avg, max, min, amounts.size());
    }

    private BigDecimal computeAvg(List<BigDecimal> amounts) {
        BigDecimal avg = BigDecimal.ZERO;

        for(BigDecimal number : amounts) {
            if(number != null) {
                avg = avg.add(number);
            }
        }

        return avg.divide(new BigDecimal(amounts.size()), 2, RoundingMode.HALF_UP);
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
        BigDecimal min = amounts.get(0);

        for(BigDecimal number : amounts) {
            if(min.compareTo(number) > 0) {
                min = number;
            }
        }
        return min;
    }

}

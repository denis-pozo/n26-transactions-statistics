package com.n26.domain;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Value
public class Statistics {

    private static final int decimalPlaces = 2;
    private static final RoundingMode roundingMode = RoundingMode.HALF_UP;

    @NonNull BigDecimal sum;
    @NonNull BigDecimal avg;
    @NonNull BigDecimal max;
    @NonNull BigDecimal min;
    long count;

    public Statistics
        (BigDecimal sum, BigDecimal avg, BigDecimal max, BigDecimal min, long count)
    {
        this.sum = format(sum);
        this.avg = format(avg);
        this.max = format(max);
        this.min = format(min);
        this.count = count;
    }

    protected BigDecimal format(BigDecimal number) {
        BigDecimal formatted = number.setScale(decimalPlaces, roundingMode);
        return formatted;
    }
}

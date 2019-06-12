package com.n26.domain;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class Statistics {

    @NonNull BigDecimal sum;
    @NonNull BigDecimal avg;
    @NonNull BigDecimal max;
    @NonNull BigDecimal min;
    long count;

}

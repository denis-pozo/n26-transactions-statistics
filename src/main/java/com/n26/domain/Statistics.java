package com.n26.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class Statistics {

    BigDecimal sum;
    BigDecimal avg;
    BigDecimal max;
    BigDecimal min;
    long count;

}

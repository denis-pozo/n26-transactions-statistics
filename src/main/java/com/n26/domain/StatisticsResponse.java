package com.n26.domain;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class StatisticsResponse {

    String sum;
    String avg;
    String max;
    String min;
    long count;
}

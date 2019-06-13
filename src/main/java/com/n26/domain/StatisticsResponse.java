package com.n26.domain;

import lombok.*;

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

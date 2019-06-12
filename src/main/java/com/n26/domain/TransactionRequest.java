package com.n26.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
public class TransactionRequest {

    @Getter @Setter
    private BigDecimal amount;

    @Getter @Setter
    private String timestamp;
}

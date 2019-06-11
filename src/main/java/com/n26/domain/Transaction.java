package com.n26.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class Transaction {

    BigDecimal amount;
    LocalDateTime timestamp;

}

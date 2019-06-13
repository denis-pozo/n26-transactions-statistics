package com.n26.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class TransactionRequest {

    @Getter @Setter
    private String amount;

    @Getter @Setter
    private String timestamp;
}

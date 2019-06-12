package com.n26.controller;

import com.n26.domain.Transaction;
import com.n26.domain.TransactionRequest;
import com.n26.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private static final HttpStatus SUCCESS = HttpStatus.CREATED;
    private static final HttpStatus DEPRECATED_TRANSACTION = HttpStatus.NO_CONTENT;
    private static final HttpStatus INVALID_JSON = HttpStatus.BAD_REQUEST;
    private static final HttpStatus PARSING_ERROR = HttpStatus.UNPROCESSABLE_ENTITY;

    @Autowired
    TransactionService transactionService;

    // TODO Detect when it is an invalid JSON
    @PostMapping
    public ResponseEntity addTransaction(@RequestBody TransactionRequest request) {
        BigDecimal amount = request.getAmount();
        String timestamp = request.getTimestamp();

        try {
            Transaction transaction = new Transaction(amount, Instant.parse(timestamp));
            transactionService.addTransaction(transaction);
        } catch (NullPointerException npe ) {
            return new ResponseEntity(INVALID_JSON);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity(DEPRECATED_TRANSACTION);
        }

        return new ResponseEntity(SUCCESS);
    }

    @DeleteMapping
    public ResponseEntity deleteAllTransactions() {
        if (transactionService.deleteAllTransactions()) {
            return new ResponseEntity(SUCCESS);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

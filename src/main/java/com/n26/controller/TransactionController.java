package com.n26.controller;

import com.n26.domain.Transaction;
import com.n26.domain.TransactionRequest;
import com.n26.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Instant;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";
    private static final int CODE_SUCCESS = 201;
    private static final int DEPRECATED_TRANSACTION = 204;
    private static final int INVALID_JSON = 400;
    private static final int PARSING_ERROR = 422;

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
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

}

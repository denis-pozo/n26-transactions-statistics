package com.n26.controller;

import com.n26.domain.Transaction;
import com.n26.domain.TransactionRequest;
import com.n26.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Instant;
import java.time.format.DateTimeParseException;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private static final HttpStatus SUCCESS = HttpStatus.CREATED;
    private static final HttpStatus UNPARSEABLE_TRANSACTION = HttpStatus.NO_CONTENT;
    private static final HttpStatus INVALID_JSON = HttpStatus.BAD_REQUEST;
    private static final HttpStatus PARSING_ERROR = HttpStatus.UNPROCESSABLE_ENTITY;

    @Autowired
    TransactionService transactionService;

    // TODO Detect when it is an invalid JSON
    @PostMapping
    @ApiOperation(value = "Add transaction to the transactions list, if it is dated within last 60 secs.")
    public ResponseEntity addTransaction(@RequestBody TransactionRequest request) {

        try {
            BigDecimal amount = request.getAmount();
            Instant timestamp = Instant.parse(request.getTimestamp());
            Transaction transaction = new Transaction(amount, timestamp);
            if(transactionService.addTransaction(transaction)) {
                return new ResponseEntity(UNPARSEABLE_TRANSACTION);
            }
        } catch (NullPointerException npe ) {
            return new ResponseEntity(INVALID_JSON);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity(UNPARSEABLE_TRANSACTION);
        } catch (DateTimeParseException pe) {
            return new ResponseEntity(PARSING_ERROR);
        }

        return new ResponseEntity(SUCCESS);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete current transactions")
    public ResponseEntity deleteAllTransactions() {
        if (transactionService.deleteAllTransactions()) {
            return new ResponseEntity(UNPARSEABLE_TRANSACTION);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

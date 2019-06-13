package com.n26.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.n26.domain.Transaction;
import com.n26.domain.TransactionRequest;
import com.n26.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.format.DateTimeParseException;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

    private static final HttpStatus SUCCESS_201 = HttpStatus.CREATED;
    private static final HttpStatus NO_CONTENT_204 = HttpStatus.NO_CONTENT;
    private static final HttpStatus INVALID_JSON_400 = HttpStatus.BAD_REQUEST;
    private static final HttpStatus PARSING_ERROR_422 = HttpStatus.UNPROCESSABLE_ENTITY;

    @Autowired
    TransactionService transactionService;

    @PostMapping
    @ApiOperation(value = "Add transaction to the transactions list, if it is dated within last 60 secs.")
    public ResponseEntity addTransaction(@RequestBody TransactionRequest request) {

        try {
            BigDecimal amount = new BigDecimal(request.getAmount());
            Instant timestamp = Instant.parse(request.getTimestamp());

            Transaction transaction = new Transaction(amount, timestamp);
            if(!transactionService.addTransaction(transaction)) {
                return new ResponseEntity(NO_CONTENT_204);
            }
        } catch (NullPointerException npe) {
            return new ResponseEntity(INVALID_JSON_400);
        } catch (NumberFormatException nfe) {
            return new ResponseEntity(PARSING_ERROR_422);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity(PARSING_ERROR_422);
        } catch (HttpMessageNotReadableException hmnre) {
            return new ResponseEntity(PARSING_ERROR_422);
        } catch (DateTimeParseException pe) {
            return new ResponseEntity(PARSING_ERROR_422);
        }

        return new ResponseEntity(SUCCESS_201);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete current transactions")
    public ResponseEntity deleteAllTransactions() {
        if (transactionService.deleteAllTransactions()) {
            return new ResponseEntity(NO_CONTENT_204);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

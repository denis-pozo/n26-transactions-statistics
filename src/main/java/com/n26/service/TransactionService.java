package com.n26.service;

import com.n26.domain.Transaction;

import java.util.List;

public interface TransactionService {

    boolean addTransaction(Transaction transaction);
    List<Transaction> getTransactions();
    boolean deleteAllTransactions();
}

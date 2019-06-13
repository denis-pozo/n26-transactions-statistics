package com.n26.service;

import com.n26.domain.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions();
    boolean addTransaction(Transaction transaction);
    boolean deleteAllTransactions();
}

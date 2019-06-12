package com.n26.service;

import com.n26.domain.Transaction;

import java.time.Instant;
import java.util.*;

public class TransactionServiceImpl implements TransactionService {

    private List<Transaction> transactions = new ArrayList<Transaction>();

    @Override
    public List<Transaction> getAllTransactions() {
        updateList();
        return transactions;
    }

    private void updateList() {
        Instant now = Instant.now();
        // TODO update transactions deleting obsolete transaction (older than 60 secs)
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transactions.sort(Comparator.comparing(Transaction::getTimestamp));
    }

    @Override
    public boolean deleteAllTransactions() {
        transactions.clear();
        return true;
    }
}

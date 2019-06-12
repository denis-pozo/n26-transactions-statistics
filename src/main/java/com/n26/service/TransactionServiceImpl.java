package com.n26.service;

import com.n26.domain.Transaction;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    private List<Transaction> transactions = new ArrayList<>();

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
    public boolean addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transactions.sort(Comparator.comparing(Transaction::getTimestamp));
        return false;
    }

    @Override
    public boolean deleteAllTransactions() {
        transactions.clear();
        return true;
    }
}

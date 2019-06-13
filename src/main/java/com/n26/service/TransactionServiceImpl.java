package com.n26.service;

import com.n26.domain.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final BlockingQueue<Transaction> transactions =
            new PriorityBlockingQueue<>(
                   20,
                    (o1, o2) -> {
                        if(o1.getTimestamp().isBefore(o2.getTimestamp())) {
                            return 1;
                        } else {
                            return -1;
                        }
                    });

    @Override
    public boolean addTransaction(Transaction transaction) {
        log.info("Trying to store transaction...");
        if(isOld(transaction)) {
            log.info("Discarded transaction: transaction older than 60 secs");
            return false;
        }

        try {
            transactions.put(transaction);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        log.info("Transaction has been successfully added");
        return true;
    }

    @Override
    public List<Transaction> getTransactions() {
        updateTransactions();
        return new ArrayList<>(transactions);
    }

    @Override
    public boolean deleteAllTransactions() {
        transactions.clear();
        return true;
    }

    @Scheduled(fixedRate = 60 * 1000)
    private void updateTransactions() {
        log.info("Updating list of transactions ...");

        while (transactions.size() > 0 && isOld(transactions.peek())) {
            try {
                transactions.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isOld(Transaction transaction) {
        if(transaction.getTimestamp().isBefore(Instant.now().minusSeconds(60))){
            return true;
        } else {
            return false;
        }
    }

}

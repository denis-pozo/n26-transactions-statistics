package com.n26.service;

import com.n26.domain.Statistics;
import com.n26.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Override
    public Statistics computeStatistics(List<Transaction> transactions) {
        return null;
    }
}

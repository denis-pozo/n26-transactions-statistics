package com.n26.service;

import com.n26.domain.Statistics;
import com.n26.domain.Transaction;

import java.util.List;

public interface StatisticsService {

    Statistics computeStatistics(List<Transaction> transactions);

}

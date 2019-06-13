package com.n26.service;

import com.n26.domain.StatisticsResponse;
import com.n26.domain.Transaction;

import java.util.List;

public interface StatisticsService {

    StatisticsResponse getStatistics(List<Transaction> transactions);

}

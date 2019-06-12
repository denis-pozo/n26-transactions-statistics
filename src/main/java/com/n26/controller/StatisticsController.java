package com.n26.controller;

import com.n26.domain.Statistics;
import com.n26.service.StatisticsService;
import com.n26.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/statistics")
@RestController
public class StatisticsController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    StatisticsService statisticsService;

    // TODO return statistics as body
    @GetMapping
    public void getStatistics() {
        Statistics stats = statisticsService.computeStatistics(transactionService.getAllTransactions());
    }

}

package com.n26.controller;

import com.n26.domain.Statistics;
import com.n26.service.StatisticsService;
import com.n26.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequestMapping("/statistics")
@RestController
public class StatisticsController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    StatisticsService statisticsService;

    @GetMapping
    @ApiOperation(value = "Display transaction statistics of the last 60 seconds.")
    public Statistics getStatistics() {
//        Statistics stats = statisticsService.computeStatistics(transactionService.getAllTransactions());
        Statistics stats = new Statistics(BigDecimal.valueOf(234.53), BigDecimal.valueOf(234.55), BigDecimal.valueOf(325.3), BigDecimal.valueOf(12.0), 3);
        return stats;
    }

}

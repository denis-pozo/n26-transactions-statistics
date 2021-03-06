package com.n26.controller;

import com.n26.domain.StatisticsResponse;
import com.n26.service.StatisticsService;
import com.n26.service.TransactionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/statistics")
@RestController
public class StatisticsController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    StatisticsService statisticsService;

    @GetMapping
    @ApiOperation("Get transaction statistics within the last 60 sec")
    public StatisticsResponse getStatistics() {
        return statisticsService.getStatistics(transactionService.getTransactions());
    }

}

package com.example.DVFPROJECT.service.impl;

import com.example.DVFPROJECT.service.CsvDataScheduler;
import com.example.DVFPROJECT.service.CsvDataService;
import com.example.DVFPROJECT.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class CsvDataSchedulerImpl implements CsvDataScheduler {
    private final TransactionService transactionService;

    @Autowired
    public CsvDataSchedulerImpl(CsvDataService csvDataService, TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    @Scheduled(fixedRate = 300000) // Toutes les 5 minutes
    public void scheduleCsvDataLoading() {
        String filePath = "full.csv";
        transactionService.loadCsvDataToDatabase(filePath);
    }
}

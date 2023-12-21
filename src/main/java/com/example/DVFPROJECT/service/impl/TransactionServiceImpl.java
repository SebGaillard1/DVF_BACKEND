package com.example.DVFPROJECT.service.impl;

import com.example.DVFPROJECT.business.Transaction;
import com.example.DVFPROJECT.repository.TransactionRepository;
import com.example.DVFPROJECT.service.CsvDataService;
import com.example.DVFPROJECT.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository repository;
    private CsvDataService csvDataService;

    public void loadCsvDataToDatabase(String filePath) {
        List<Transaction> transactions = csvDataService.readCsvData(filePath);
        repository.saveAll(transactions);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return repository.findAll();
    }
    // ... autres méthodes du service
}

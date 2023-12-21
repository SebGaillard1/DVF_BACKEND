package com.example.DVFPROJECT.service;

import com.example.DVFPROJECT.business.Transaction;

import java.util.List;

public interface TransactionService {
    void loadCsvDataToDatabase(String filePath);

    List<Transaction> findAllTransactions();

    List<Transaction> findFirst10Transactions();
}

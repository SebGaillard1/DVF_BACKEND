package com.example.DVFPROJECT.service;

import com.example.DVFPROJECT.business.Transaction;
import com.example.DVFPROJECT.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    void loadCsvDataToDatabase(String filePath);

    List<TransactionDTO> findAllTransactions();

    List<TransactionDTO> findFirst10Transactions();
}

package com.example.DVFPROJECT.service;

import com.example.DVFPROJECT.business.Transaction;

import java.util.List;

public interface CsvDataService {
    List<Transaction> readCsvData(String filePath);
}

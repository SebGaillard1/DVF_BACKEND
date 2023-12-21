package com.example.DVFPROJECT.service.impl;

import com.example.DVFPROJECT.business.Transaction;
import com.example.DVFPROJECT.dto.TransactionDTO;
import com.example.DVFPROJECT.repository.TransactionRepository;
import com.example.DVFPROJECT.service.CsvDataService;
import com.example.DVFPROJECT.service.TransactionService;
import com.example.DVFPROJECT.mapper.TransactionMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository repository;
    private CsvDataService csvDataService;
    private TransactionMapper mapper;

    public void loadCsvDataToDatabase(String filePath) {
        List<Transaction> transactions = csvDataService.readCsvData(filePath);
        repository.saveAll(transactions);
    }

    @Override
    public List<TransactionDTO> findAllTransactions() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> findFirst10Transactions() {
        Pageable pageable = PageRequest.of(0, 10);
        return repository.findAll(pageable)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
    // ... autres méthodes du service
}

package com.example.DVFPROJECT.controller;

import com.example.DVFPROJECT.business.Transaction;
import com.example.DVFPROJECT.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/first10")
    public ResponseEntity<List<Transaction>> getFirst10Transactions() {
        List<Transaction> transactions = transactionService.findFirst10Transactions();
        return ResponseEntity.ok(transactions);
    }
}

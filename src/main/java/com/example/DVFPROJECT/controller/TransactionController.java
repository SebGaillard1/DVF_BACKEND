package com.example.DVFPROJECT.controller;

import com.example.DVFPROJECT.business.Transaction;
import com.example.DVFPROJECT.dto.TransactionDTO;
import com.example.DVFPROJECT.service.PdfGenerationService;
import com.example.DVFPROJECT.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final PdfGenerationService pdfGenerationService;


    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactionsDTO = transactionService.findAllTransactions();
        return ResponseEntity.ok(transactionsDTO);
    }
    @GetMapping("/first10")
    public ResponseEntity<List<TransactionDTO>> getFirst10Transactions() {
        List<TransactionDTO> transactionsDTO = transactionService.findFirst10Transactions();
        return ResponseEntity.ok(transactionsDTO);
    }

    @GetMapping("/radius")
    public ResponseEntity<List<TransactionDTO>> getTransactionsInRadius(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius) {
        List<TransactionDTO> transactionsDTO = transactionService.findTransactionsInRadius(latitude, longitude, radius);
        return ResponseEntity.ok(transactionsDTO);
    }

    @GetMapping("/generatePdf")
    public ResponseEntity<byte[]> generatePdf(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius) {
        List<TransactionDTO> transactionsDTO = transactionService.findTransactionsInRadius(latitude, longitude, radius);
        byte[] pdfContent = pdfGenerationService.generatePdfReport(transactionsDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("transactions_report.pdf").build());
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }
}

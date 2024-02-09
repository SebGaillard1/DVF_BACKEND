package com.example.DVFPROJECT.controller;

import com.example.DVFPROJECT.dto.TransactionDTO;
import com.example.DVFPROJECT.service.PdfGenerationService;
import com.example.DVFPROJECT.service.PdfQueueService;
import com.example.DVFPROJECT.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;
    private final PdfGenerationService pdfGenerationService;
    private final PdfQueueService pdfQueueService;

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
    public ResponseEntity<String> generatePdf(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius) {
        List<TransactionDTO> transactionsDTO = transactionService.findTransactionsInRadius(latitude, longitude, radius);
        pdfQueueService.addToQueue(transactionsDTO);

        return ResponseEntity.ok("Demande de PDF ajoutée à la file d'attente");
    }

    @GetMapping("/downloadPdf")
    public ResponseEntity<Resource> downloadPdf() {
        String filename = "rapport.pdf";
        Resource file = new FileSystemResource(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}

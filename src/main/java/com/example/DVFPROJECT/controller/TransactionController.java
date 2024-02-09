package com.example.DVFPROJECT.controller;

import com.example.DVFPROJECT.business.Transaction;
import com.example.DVFPROJECT.dto.TransactionDTO;
import com.example.DVFPROJECT.service.NotificationService;
import com.example.DVFPROJECT.service.PdfGenerationService;
import com.example.DVFPROJECT.service.PdfQueueService;
import com.example.DVFPROJECT.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;
    private final PdfGenerationService pdfGenerationService;
    private final PdfQueueService pdfQueueService;
    private final NotificationService notificationService;
    private final SimpMessagingTemplate template; // WebSocket messaging template

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

//    @GetMapping("/generatePdf")
//    public ResponseEntity<byte[]> generatePdf(
//            @RequestParam double latitude,
//            @RequestParam double longitude,
//            @RequestParam double radius) {
//        List<TransactionDTO> transactionsDTO = transactionService.findTransactionsInRadius(latitude, longitude, radius);
//        byte[] pdfContent = pdfGenerationService.generatePdfReport(transactionsDTO);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("transactions_report.pdf").build());
//        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
//    }

    @GetMapping("/generatePdf")
    public ResponseEntity<String> generatePdf(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius) {
        List<TransactionDTO> transactionsDTO = transactionService.findTransactionsInRadius(latitude, longitude, radius);
        pdfQueueService.addToQueue(transactionsDTO);

        // Envoyer une notification via WebSocket indiquant que le processus est démarré
        template.convertAndSend("/topic/pdfStatus", "Début de la génération du PDF");


        // Déclenchez le traitement de la file d'attente si nécessaire
        //pdfQueueService.processQueue();

        return ResponseEntity.ok("Demande de PDF ajoutée à la file d'attente");
    }

    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        return notificationService.createEmitter();
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

package com.example.DVFPROJECT.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import com.example.DVFPROJECT.dto.TransactionDTO;
import com.example.DVFPROJECT.service.NotificationService;
import com.example.DVFPROJECT.service.PdfGenerationService;
import com.example.DVFPROJECT.service.PdfQueueService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class PdfQueueServiceImpl implements PdfQueueService {
    private final BlockingQueue<List<TransactionDTO>> queue = new LinkedBlockingQueue<>();
    private final PdfGenerationService pdfGenerationService;
    private final NotificationService notificationService;

    @Autowired
    public PdfQueueServiceImpl(PdfGenerationService pdfGenerationService, NotificationService notificationService) {
        this.pdfGenerationService = pdfGenerationService;
        this.notificationService = notificationService;
    }

    @Override
    public void addToQueue(List<TransactionDTO> transactions) {
        queue.add(transactions);
    }

    @Override
    @Async
    public void processQueue() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                List<TransactionDTO> transactions = queue.take();
                byte[] pdfContent = pdfGenerationService.generatePdfReport(transactions);

                // Nom de fichier statique
                String filename = "rapport.pdf";

                // Stocker le PDF à la racine du projet
                try (FileOutputStream fos = new FileOutputStream(filename)) {
                    fos.write(pdfContent);
                }

                // Envoyer une notification globale
                notificationService.sendGlobalNotification("Le PDF est prêt");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


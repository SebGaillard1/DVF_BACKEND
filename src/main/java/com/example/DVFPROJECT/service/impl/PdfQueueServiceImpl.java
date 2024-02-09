package com.example.DVFPROJECT.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import com.example.DVFPROJECT.dto.TransactionDTO;
import com.example.DVFPROJECT.service.PdfGenerationService;
import com.example.DVFPROJECT.service.PdfQueueService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PdfQueueServiceImpl implements PdfQueueService {
    private final BlockingQueue<List<TransactionDTO>> queue = new LinkedBlockingQueue<>();
    private final PdfGenerationService pdfGenerationService;
    private final AtomicBoolean isProcessing = new AtomicBoolean(false);


    @Autowired
    public PdfQueueServiceImpl(PdfGenerationService pdfGenerationService ) {
        this.pdfGenerationService = pdfGenerationService;
    }

    @Override
    public void addToQueue(List<TransactionDTO> transactions) {
        queue.add(transactions);
        if (isProcessing.compareAndSet(false, true)) {
            processQueue();
        }
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

            } catch (InterruptedException | IOException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


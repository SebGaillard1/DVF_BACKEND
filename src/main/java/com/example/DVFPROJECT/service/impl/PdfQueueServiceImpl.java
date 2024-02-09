package com.example.DVFPROJECT.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import com.example.DVFPROJECT.dto.TransactionDTO;
import com.example.DVFPROJECT.service.PdfGenerationService;
import com.example.DVFPROJECT.service.PdfQueueService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class PdfQueueServiceImpl implements PdfQueueService {
    private final BlockingQueue<List<TransactionDTO>> queue = new LinkedBlockingQueue<>();
    private final PdfGenerationService pdfGenerationService;

    @Autowired
    public PdfQueueServiceImpl(PdfGenerationService pdfGenerationService) {
        this.pdfGenerationService = pdfGenerationService;
    }

    @Override
    public void addToQueue(List<TransactionDTO> transactions) {
        queue.add(transactions);
        processQueue();
    }

    @Override
    public void processQueue() {
        while (!queue.isEmpty()) {
            try {
                List<TransactionDTO> transactions = queue.take();
                byte[] pdfContent = pdfGenerationService.generatePdfReport(transactions);

                String filename = "rapport.pdf";

                try (FileOutputStream fos = new FileOutputStream(filename)) {
                    fos.write(pdfContent);
                }

            } catch (InterruptedException | IOException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

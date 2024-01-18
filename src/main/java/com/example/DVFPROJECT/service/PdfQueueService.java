package com.example.DVFPROJECT.service;

import java.util.List;
import com.example.DVFPROJECT.dto.TransactionDTO;

public interface PdfQueueService {
    void addToQueue(List<TransactionDTO> transactions);
    void processQueue();
}

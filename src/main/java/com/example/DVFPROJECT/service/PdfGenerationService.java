package com.example.DVFPROJECT.service;

import com.example.DVFPROJECT.dto.TransactionDTO;

import java.util.List;

public interface PdfGenerationService {
    byte[] generatePdfReport(List<TransactionDTO> transactions);
}

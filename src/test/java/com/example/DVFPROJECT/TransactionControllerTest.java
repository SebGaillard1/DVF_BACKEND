package com.example.DVFPROJECT;

import com.example.DVFPROJECT.controller.TransactionController;
import com.example.DVFPROJECT.service.PdfQueueService;
import com.example.DVFPROJECT.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private PdfQueueService pdfQueueService;

    @Test
    void testGeneratePdf() throws Exception {
        double latitude = 40.712776;
        double longitude = -74.005974;
        double radius = 1000;

        mockMvc.perform(get("/api/transactions/generatePdf")
                        .param("latitude", String.valueOf(latitude))
                        .param("longitude", String.valueOf(longitude))
                        .param("radius", String.valueOf(radius))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

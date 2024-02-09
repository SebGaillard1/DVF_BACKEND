package com.example.DVFPROJECT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketMessageController {

    private final SimpMessagingTemplate template;

    @Autowired
    public WebSocketMessageController(SimpMessagingTemplate template) {
        this.template = template;
    }

    // Exemple de méthode pour envoyer des notifications
    public void notifyPdfReady(String message) {
        template.convertAndSend("/topic/pdfReady", message);
    }
}

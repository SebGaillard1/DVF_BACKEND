package com.example.DVFPROJECT.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {
    SseEmitter createEmitter();
    void sendGlobalNotification(String message);
}

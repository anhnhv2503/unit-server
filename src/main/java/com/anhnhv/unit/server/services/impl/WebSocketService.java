package com.anhnhv.unit.server.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class WebSocketService {

    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public void sendToOne(WebSocketSession webSocketSession, String message) throws IOException {
        webSocketSession.sendMessage(new TextMessage(message));
    }

    public void sendToOneClient(WebSocketSession webSocketSession, Object obj) throws IOException {
        String json = objectMapper.writeValueAsString(obj);
        sendToOne(webSocketSession, json);
    }

    public void sendToAllClient(Object obj){
        try {
            sendToAll(objectMapper.writeValueAsString(obj));
        }catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(String message) {
        TextMessage msg = new TextMessage(message);
        sessions.forEach(session -> {
            try {
                session.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

}

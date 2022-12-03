package com.example.testworkshop.service;

import org.springframework.stereotype.Service;

@Service
public class OpenBrokerService {

    public void message(String id, String message) {
        System.out.printf("====== SENDING BROKER MESSAGE '%s' FOR ID %s%n", id, message);
    }

    public void rejection(String id) {
        System.out.printf("====== SENDING REJECTION FOR ID %s%n", id);
    }

    public void accept(String id) {
        System.out.printf("====== SENDING ACCEPT FOR ID %s%n", id);
    }
}

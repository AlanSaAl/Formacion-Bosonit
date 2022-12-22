package com.example.block12kafkaconsumidor;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "topicoTest", groupId = "groupId")
    public void listenGroupFoo(String message) {
        System.out.println("Mensaje recibido: " + message);
    }
}

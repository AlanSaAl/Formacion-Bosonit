package com.example.block12kafkaemisor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mensaje")
public class ProducerController {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void enviarMensaje(@RequestBody String mensaje) {
        kafkaTemplate.send("topicoTest", mensaje);
    }
}

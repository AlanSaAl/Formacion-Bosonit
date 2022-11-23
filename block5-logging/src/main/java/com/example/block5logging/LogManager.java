package com.example.block5logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class LogManager {
    Logger logger = LoggerFactory.getLogger(LogManager.class);

    @RequestMapping("/")
    public String index() {
        logger.trace("Mensaje a nivel de TRACE");
        logger.debug("Mensaje a nivel de DEBUG");
        logger.info("Mensaje a nivel de INFO");
        logger.warn("Mensaje a nivel de WARNING");
        logger.error("Mensaje a nivel de ERROR");

        return "Hola! Mira los logs para ver los resultados";
    }
}

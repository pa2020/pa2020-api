package fr.esgi.api.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);


    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${jsa.kafka.topic}")
    String kafkaTopic = "jsa-test";

    public void send(String request) {
        log.info("sending data=" + request);
        kafkaTemplate.send(kafkaTopic, request);
    }
}

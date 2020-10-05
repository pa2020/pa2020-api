package fr.esgi.api.kafka;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public class KafkaProducerRequest {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducerRequest.class);
    private final KafkaTemplate<String, String> kafkaProducer;
    @Value("${cloudkarafka.topic}")
    String kafkaTopic;

//    public void sendMe(String request) {
//        log.info("< sending data To topic :{}", request);
//        kafkaProducer.send(kafkaTopic, request);
//    }

    public void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future =
                kafkaProducer.send(kafkaTopic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }
}


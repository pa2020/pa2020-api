//package fr.esgi.api.broker;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageBuilder;
//import org.springframework.amqp.core.MessageDeliveryMode;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class Producer {
//
//    private final RabbitTemplate rabbitTemplates;
//
//    @Value("${rabbitmq.exchange}")
//    private String exchange;
//
//    @Value("${rabbitmq.routingKey}")
//    private String routingkey;
//
//    public void sendMessage(final String msg) {
//        Message message = MessageBuilder
//                .withBody(msg.getBytes())
//                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
//                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
//                .build();
//        this.rabbitTemplates.convertAndSend(exchange, routingkey, message);
//        log.info("Send Object = " + message);
//    }
//}

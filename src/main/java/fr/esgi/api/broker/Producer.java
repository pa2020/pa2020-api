package fr.esgi.api.broker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class Producer {

    private final ObjectMapper objectMapper;
    private final ConnectionFactory factory;
    private Channel channel;
    private Connection connection;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.queue}")
    private String nameQueue;

    @Value("${rabbitmq.routingKey}")
    private String routingkey;

    public Producer(ObjectMapper objectMapper) throws IOException, TimeoutException {
        this.objectMapper = objectMapper;
        this.factory = new ConnectionFactory();
        factory.setHost("hawk.rmq.cloudamqp.com");
        factory.setVirtualHost("sotsexut");
        this.connection = factory.newConnection();
        this.channel = connection.createChannel();
        factory.setPort(5672);
        factory.setUsername("sotsexut");
        factory.setPassword("5k05KV2DnwaohQMZxIkYFNrPbu6DJjrc");
    }

    public void sendMessage(final String msg) throws IOException {
        try {
            String msgJson = objectMapper.writeValueAsString(msg);
            channel.basicPublish("", nameQueue, null, msgJson.getBytes());
            log.info("Send Object = " + msg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

//
//    public void sendMessage(final String msg) {
//        try {
//            String msgJson = objectMapper.writeValueAsString(msg);
//            Message message = MessageBuilder
//                    .withBody(msgJson.getBytes())
//                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
//                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
//                    .build();
//            //this.rabbitTemplate.convertAndSend(queueName, message);
//            this.rabbitTemplate.convertAndSend(exchange, routingkey, message);
//            log.info("Send Object = " + msg);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
}

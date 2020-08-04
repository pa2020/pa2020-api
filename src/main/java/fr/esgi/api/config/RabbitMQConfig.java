package fr.esgi.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig implements RabbitListenerConfigurer {

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingkey;

    @Value("${rabbitmq.queue}")
    private String queueName;

//    @Value("${rabbitmq.deadLetter.queue}")
//    private String deadLetterQueue;
//
//    @Value("${rabbitmq.deadLetterKey}")
//    private String deadLetter;
//
//    @Value("${rabbitmq.dlqExchange}")
//    private String deadLetterExchange;

    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }

    @Bean
    public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(jackson2Converter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
    }

//    @Bean
//    Queue dlq() {
//        return QueueBuilder.durable(deadLetterQueue).build();
//    }
//
//    @Bean
//    Queue queue() {
//        return QueueBuilder.durable(queueName).withArgument("x-dead-letter-exchange", deadLetterExchange)
//                .withArgument("x-dead-letter-routing-key", deadLetter).build();
//    }
//
//    @Bean
//    DirectExchange deadLetterExchange() {
//        return new DirectExchange(deadLetterExchange);
//    }

    @Bean
    Queue queue() {
        return new Queue(queueName, true, false, false, null);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange, true, false, null);
    }

//    @Bean
//    Binding DLQbinding() {
//        return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with(deadLetter);
//    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingkey);
    }

//    @Bean
//    public TaskReceiver receiver() {
//        return new TaskReceiver();
//    }
}


//package fr.esgi.api.broker;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
//import org.springframework.stereotype.Component;
//
//import java.nio.channels.Channel;
//
//@Component
//public class TaskReceiver implements ChannelAwareMessageListener {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Override
//    public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
//        logger.info("Message received.");
//        // do something with the message
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
//}

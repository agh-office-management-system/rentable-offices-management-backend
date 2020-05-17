package pl.edu.agh.rentableoffices.messaging.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class UserMessagingConfiguration {
    @Value("${message.queue.name}")
    String queueName;

    @Value("${message.routingKey.name}")
    private String routingKey;

    @Bean
    Queue userMessageQueue() {
        log.info("Creating user message queue \"{}\"", queueName);
        return new Queue(queueName, false);
    }

    @Bean
    Binding userMessageBinding(Queue queue, DirectExchange exchange) {
        log.info("Binding queue \"{}\" with exchange \"{}\" with routing key = {}", queueName, exchange.getName(), routingKey);
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

}

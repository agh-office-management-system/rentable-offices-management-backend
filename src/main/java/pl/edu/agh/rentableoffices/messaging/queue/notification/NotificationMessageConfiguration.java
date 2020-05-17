package pl.edu.agh.rentableoffices.messaging.queue.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class NotificationMessageConfiguration {
    @Value("${notification.queue.name}")
    String queueName;

    @Value("${notification.routingKey.name}")
    private String routingKey;

    @Bean
    Queue userNotificationQueue() {
        log.info("Creating user notification queue {}", queueName);
        return new Queue(queueName, false);
    }

    @Bean
    Binding userNotificationBinding(@Qualifier("userNotificationQueue") Queue queue, DirectExchange exchange) {
        log.info("Binding queue {} with exchange {} with routing key = {}", queueName, exchange.getName(), routingKey);
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
}

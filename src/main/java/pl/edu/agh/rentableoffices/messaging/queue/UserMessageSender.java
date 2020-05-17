package pl.edu.agh.rentableoffices.messaging.queue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.model.UserMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMessageSender {

    private final RabbitTemplate template; //TODO move to superclass

    @Value("${offices.exchange.name}")
    private String exchange; //TODO move to superclass

    @Value("${message.routingKey.name}")
    private String routingKey; //TODO move to superclass and leave @Value in constructor

    public void send(UserMessage userMessage) {
        log.info("Sending message to user {} from {}", userMessage.getTo(), userMessage.getFrom());
        template.convertAndSend(exchange, routingKey, userMessage);
    }
}

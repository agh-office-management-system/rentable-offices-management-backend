package pl.edu.agh.rentableoffices.messaging.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.model.UserMessage;

@Slf4j
@Service
public class UserMessageListener {

    @RabbitListener(queues = "${message.queue.name}")
    public void handle(UserMessage message) {
        log.info("Received message from {} to {}", message.getFrom(), message.getTo());
    }
}

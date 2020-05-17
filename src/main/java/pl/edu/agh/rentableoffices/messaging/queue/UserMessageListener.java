package pl.edu.agh.rentableoffices.messaging.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.common.queue.MessageBase;

@Slf4j
@Service
public class UserMessageListener {

    @RabbitListener(queues = "${message.queue.name}")
    public void handle(MessageBase<UserMessageDto> message) {
        log.info("Received message from {} to {}", message.getContent().getFrom(), message.getContent().getTo());
    }
}

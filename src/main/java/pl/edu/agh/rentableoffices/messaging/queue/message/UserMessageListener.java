package pl.edu.agh.rentableoffices.messaging.queue.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.common.queue.MessageBase;
import pl.edu.agh.rentableoffices.messaging.dao.MessageRepository;
import pl.edu.agh.rentableoffices.messaging.model.UserMessage;
import pl.edu.agh.rentableoffices.messaging.service.NotificationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMessageListener {
    private final MessageRepository messages;
    private final NotificationService notifications;

    @RabbitListener(queues = "${message.queue.name}")
    public void handle(MessageBase<UserMessageDto> message) {
        log.info("Received message from {} to {}", message.getContent().getFrom(), message.getContent().getTo());

        UserMessageDto dto = message.getContent();

        UserMessage userMessage = UserMessage.create(dto.getFrom(), dto.getTo(), dto.getContent(), message.getCreatedAt());
        Long id = messages.save(userMessage).getId();
        notifications.createMessageNotification(dto.getFrom(), dto.getTo(), id);
    }
}

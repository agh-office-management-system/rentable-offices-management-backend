package pl.edu.agh.rentableoffices.messaging.queue.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.common.queue.MessageBase;
import pl.edu.agh.rentableoffices.messaging.dao.NotificationRepository;
import pl.edu.agh.rentableoffices.messaging.model.Notification;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationListener {
    private final NotificationRepository notifications;

    @RabbitListener(queues = "${notification.queue.name}")
    public void handle(MessageBase<NotificationMessageDto> message) {
        log.info("Received notification of type {} from {} to {}", message.getContent().getNotificationType(), message.getContent().getFrom(), message.getContent().getReceivers());

        var dto = message.getContent();
        var createdNotifications = dto.getReceivers()
                .stream()
                .map(r -> Notification.create(dto.getFrom(), r, dto.getNotificationType(), dto.getPayload()))
                .collect(Collectors.toList());

        notifications.saveAll(createdNotifications);

    }
}

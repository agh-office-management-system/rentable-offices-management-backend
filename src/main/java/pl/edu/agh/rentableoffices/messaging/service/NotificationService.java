package pl.edu.agh.rentableoffices.messaging.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.dao.NotificationRepository;
import pl.edu.agh.rentableoffices.messaging.dto.NotificationDto;
import pl.edu.agh.rentableoffices.messaging.mapper.NotificationMapper;
import pl.edu.agh.rentableoffices.messaging.model.Notification;
import pl.edu.agh.rentableoffices.messaging.model.NotificationType;
import pl.edu.agh.rentableoffices.messaging.queue.notification.NotificationMessageDto;
import pl.edu.agh.rentableoffices.messaging.queue.notification.NotificationSender;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {
    private static final String SYSTEM_USER = "SYSTEM";

    private final NotificationRepository repository;
    private final NotificationMapper mapper;
    private final NotificationSender sender;

    public void createMessageNotification(String from, String to, Long messageId) {
        log.info("Sending message sent notification to {} from {}", to, from);

        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(from)
                .to(to)
                .notificationType(NotificationType.MESSAGE_SENT)
                .payload(new Object[]{messageId})
                .build();

        sender.send(message);
    }


    public void createMessageReadNotification(String to, Long messageId) {
        log.info("Sending message read notification to {}", to);
        NotificationMessageDto message = NotificationMessageDto.builder()
                .from(SYSTEM_USER)
                .to(to)
                .notificationType(NotificationType.MESSAGE_READ)
                .payload(new Object[]{messageId})
                .build();

        sender.send(message);
    }

    public void notifyAdministration(NotificationType type, Object[] payload) {
        //TODO
    }

    public void notifyTenant(Tenant tenant, NotificationType type, Object[] payload) {
        //TODO
    }

    public void notifyTenants(List<Tenant> tenants, NotificationType type, Object[] payload) {
        //TODO
    }

    public List<NotificationDto> getNotifications(String username) {
        return mapper.toDtoList(repository.getAllByTo(username));
    }
}

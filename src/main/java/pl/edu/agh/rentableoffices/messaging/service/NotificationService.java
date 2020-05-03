package pl.edu.agh.rentableoffices.messaging.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.dao.NotificationRepository;
import pl.edu.agh.rentableoffices.messaging.dto.NotificationDto;
import pl.edu.agh.rentableoffices.messaging.mapper.NotificationMapper;
import pl.edu.agh.rentableoffices.messaging.model.Notification;
import pl.edu.agh.rentableoffices.messaging.model.NotificationType;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
//Rabbit
public class NotificationService {
    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    public void sendNotification(String from, String to, NotificationType type, Object[] payload) {
        Notification notification = Notification.create(from, to, type, payload);
        repository.save(notification);
    }

    public void notifyAdministration(NotificationType type, Object[] payload) {
        //TODO
    }

    public List<NotificationDto> getNotifications(String username) {
        return mapper.toDtoList(repository.getAllByTo(username));
    }
}

package pl.edu.agh.rentableoffices.messaging.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import pl.edu.agh.rentableoffices.common.AbstractMapper;
import pl.edu.agh.rentableoffices.messaging.dto.NotificationDto;
import pl.edu.agh.rentableoffices.messaging.model.Notification;
import pl.edu.agh.rentableoffices.messaging.service.NotificationTranslator;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class NotificationMapper implements AbstractMapper<Notification, NotificationDto> {

    private final NotificationTranslator translator;

    @Override
    public NotificationDto toDto(Notification entity) {
        String title = translator.getTitle(entity);
        String content = translator.getMessage(entity);
        return new NotificationDto(title,content, entity.getCreatedAt());
    }
}

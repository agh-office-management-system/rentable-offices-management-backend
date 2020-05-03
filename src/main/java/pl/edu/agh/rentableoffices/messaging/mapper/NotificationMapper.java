package pl.edu.agh.rentableoffices.messaging.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import pl.edu.agh.rentableoffices.common.AbstractMapper;
import pl.edu.agh.rentableoffices.messaging.dto.NotificationDto;
import pl.edu.agh.rentableoffices.messaging.model.Notification;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class NotificationMapper implements AbstractMapper<Notification, NotificationDto> {
    private final MessageSource messageSource;

    @Override
    public NotificationDto toDto(Notification entity) {
        String content = messageSource.getMessage(entity.getType().toString(), entity.getParams(), Locale.getDefault());
        //TODO url
        return new NotificationDto(content, null);
    }
}

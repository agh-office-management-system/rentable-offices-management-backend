package pl.edu.agh.rentableoffices.messaging.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.model.Notification;

@Service
@RequiredArgsConstructor
public class NotificationTranslator {
    private final MessageSource messageSource;

    public String getTitle(Notification notification) {
        String code = notification.getType().name();
        return messageSource.getMessage(code + ".title", getParams(notification), LocaleContextHolder.getLocale());
    }

    public String getMessage(Notification notification) {
        String code = notification.getType().name();
        return messageSource.getMessage(code + ".content", getParams(notification), LocaleContextHolder.getLocale());
    }

    private Object[] getParams(Notification notification) {
        switch (notification.getType()) {
            case MESSAGE_SENT:
                return new Object[] {notification.getFrom()};
            case MESSAGE_READ:
                return new Object[] {notification.getFrom(), notification.getCreatedAt()};
            case TENANT_CREATED:
            case TENANT_VERIFIED:
            case TENANT_REJECTED:
            case OFFICE_UPDATED:
            case SURVEY_CREATED:
                return new Object[] {notification.getParams()[0]};
            case TENANT_ASSIGNED:
            case TENANT_REMOVED:
            case SURVEY_ANSWER_SUBMITTED:
            case SURVEY_ANSWER_REJECTED:
                return new Object[] {notification.getTo(), notification.getParams()[0]};
            default:
                throw new IllegalStateException();
        }
    }
}

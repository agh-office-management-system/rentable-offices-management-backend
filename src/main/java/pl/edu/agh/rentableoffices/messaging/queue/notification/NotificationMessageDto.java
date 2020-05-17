package pl.edu.agh.rentableoffices.messaging.queue.notification;

import lombok.*;
import pl.edu.agh.rentableoffices.messaging.model.NotificationType;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationMessageDto implements Serializable {
    private String from;
    private String to;
    private NotificationType notificationType;
    private Object[] payload;
}

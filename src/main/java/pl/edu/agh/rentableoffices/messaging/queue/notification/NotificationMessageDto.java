package pl.edu.agh.rentableoffices.messaging.queue.notification;

import lombok.*;
import pl.edu.agh.rentableoffices.messaging.model.NotificationType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationMessageDto implements Serializable {
    @NotEmpty
    private String from;
    @Singular
    @NotEmpty
    private List<String> receivers;
    @NotNull
    private NotificationType notificationType;
    private Object[] payload;
}

package pl.edu.agh.rentableoffices.messaging.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import pl.edu.agh.rentableoffices.common.EntityBase;
import pl.edu.agh.rentableoffices.common.JpaConverterJson;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Notification extends EntityBase {
    @Column(name = "sender")
    private String from;

    @Column(name = "receiver")
    private String to;

    @Column(name = "notification_type")
    @Enumerated(value = EnumType.STRING)
    private NotificationType type;

    @Column(name = "params")
    @Convert(converter = JpaConverterJson.class)
    @Setter
    private Object[] params;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    public static Notification create(String from, String to, NotificationType type) {
        return new Notification(from, to, type, null, LocalDateTime.now());
    }

    public static Notification create(String from, String to, NotificationType type, Object... params) {
        return new Notification(from, to, type, params, LocalDateTime.now());
    }
}

package pl.edu.agh.rentableoffices.messaging.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.common.EntityBase;
import pl.edu.agh.rentableoffices.common.JpaConverterJson;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    private NotificationType type;

    @Convert(converter = JpaConverterJson.class)
    private Object[] params;

    public static Notification create(String from, String to, NotificationType type) {
        return new Notification(from, to, type, null);
    }

    public static Notification create(String from, String to, NotificationType type, Object... params) {
        return new Notification(from, to, type, params);
    }
}

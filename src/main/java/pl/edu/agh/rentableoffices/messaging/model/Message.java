package pl.edu.agh.rentableoffices.messaging.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import pl.edu.agh.rentableoffices.common.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Message extends EntityBase {
    @CreatedBy
    @Column(name = "sender")
    private String from;

    @NotEmpty
    @Column(name = "receiver")
    private String to;

    @NotEmpty
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    private boolean read;

    public static Message create(String from, String to, String content) {
        return new Message(from, to, content, LocalDateTime.now(),false);
    }

    public void markAsRead() {
        this.read = true;
    }
}

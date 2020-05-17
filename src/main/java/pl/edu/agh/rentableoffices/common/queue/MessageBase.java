package pl.edu.agh.rentableoffices.common.queue;

import lombok.*;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.agh.rentableoffices.security.SecurityContextHelper;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MessageBase<T extends Serializable> implements Serializable {
    private T content;
    private LocalDateTime createdAt;
    private String createdBy;

    public static <T extends Serializable> MessageBase<T> create(T payload) {
        return new MessageBase<>(payload, LocalDateTime.now(), SecurityContextHelper.getCurrentUsername());
    }
}

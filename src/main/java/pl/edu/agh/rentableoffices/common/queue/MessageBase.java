package pl.edu.agh.rentableoffices.common.queue;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MessageBase<T extends Serializable> implements Serializable {
    private T content;
    private LocalDateTime createdAt;
    //TODO created by when login mechanism is introduced

    public static <T extends Serializable> MessageBase<T> create(T payload) {
        return new MessageBase<>(payload, LocalDateTime.now());
    }
}

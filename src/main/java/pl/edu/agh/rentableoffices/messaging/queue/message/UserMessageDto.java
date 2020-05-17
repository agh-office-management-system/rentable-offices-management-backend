package pl.edu.agh.rentableoffices.messaging.queue.message;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserMessageDto implements Serializable {
    private String from;
    private String to;
    private String content;
}

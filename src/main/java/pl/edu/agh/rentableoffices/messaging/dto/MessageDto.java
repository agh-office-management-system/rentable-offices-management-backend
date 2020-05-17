package pl.edu.agh.rentableoffices.messaging.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageDto {
    private Long id;
    private String from;
    private String to;

    private String content;
    private boolean read;
    private LocalDateTime sentAt;
}

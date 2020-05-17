package pl.edu.agh.rentableoffices.messaging.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDto {
    private String name;
    private String content;
    private LocalDateTime createdAt;
}

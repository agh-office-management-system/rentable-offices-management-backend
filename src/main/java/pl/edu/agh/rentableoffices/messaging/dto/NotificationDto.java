package pl.edu.agh.rentableoffices.messaging.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationDto {
    private String content;
    private String url;
}

package pl.edu.agh.rentableoffices.messaging.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Zasób reprezentujący notyfikacje")
public class NotificationDto {
    @ApiModelProperty("Tytuł")
    private String name;
    @ApiModelProperty("Treść")
    private String content;
    @ApiModelProperty("Czas utworzenia")
    private LocalDateTime createdAt;
}

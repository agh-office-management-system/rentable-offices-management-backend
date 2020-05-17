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
@ApiModel("Wiadomość")
public class MessageDto {
    @ApiModelProperty("Id wiadomości")
    private Long id;
    @ApiModelProperty("Od kogo (email)")
    private String from;
    @ApiModelProperty("Do kogo (email)")
    private String to;

    @ApiModelProperty("Zawartość wiadomości")
    private String content;
    @ApiModelProperty("Czy przeczytana?")
    private boolean read;
    @ApiModelProperty("Data wysłania")
    private LocalDateTime sentAt;
}

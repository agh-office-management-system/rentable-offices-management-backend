package pl.edu.agh.rentableoffices.messaging.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Żądanie utworzenie wiadomości")
public class CreateMessageCommand {
    //TODO remove
    @ApiModelProperty("Od kogo (email)")
    @NotEmpty
    private String from;
    @ApiModelProperty("Do kogo (email)")
    @NotEmpty
    private String to;
    @ApiModelProperty("Treść")
    @NotEmpty
    private String content;
}

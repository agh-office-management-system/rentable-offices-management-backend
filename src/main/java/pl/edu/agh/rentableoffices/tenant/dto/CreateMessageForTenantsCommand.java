package pl.edu.agh.rentableoffices.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Zasób reprezentujący utworzenie wiadomości dla najemców")
public class CreateMessageForTenantsCommand {
    @ApiModelProperty("Id najemców")
    List<Long> tenantIds;
    @ApiModelProperty("Zawatość wiadomości")
    String content;
    @ApiModelProperty("Od kogo")
    String from;
}

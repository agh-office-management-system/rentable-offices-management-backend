package pl.edu.agh.rentableoffices.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Zasób reprezentujący utworzenie wiadomości dla najemców")
public class CreateMessageForTenantsCommand {
    @ApiModelProperty("Id najemców")
    @NotEmpty
    List<Long> tenantIds;
    @ApiModelProperty("Zawatość wiadomości")
    @NotEmpty
    String content;
    @ApiModelProperty("Od kogo")
    @NotEmpty
    String from;
}

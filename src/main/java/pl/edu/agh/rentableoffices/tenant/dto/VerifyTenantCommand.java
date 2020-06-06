package pl.edu.agh.rentableoffices.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Zasób reprezentujący żądanie weryfikacji najemcy")
public class VerifyTenantCommand {
    @ApiModelProperty("Czy zweryfikowano")
    private boolean accepted;
    @ApiModelProperty("Powód odrzucenia")
    private String rejectionReason;
}

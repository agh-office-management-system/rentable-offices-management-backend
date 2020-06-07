package pl.edu.agh.rentableoffices.office.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.agh.rentableoffices.common.AddressDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Zasób reprezentujący żądanie utworzenia biura")
public class CreateOfficeCommand {
    @PositiveOrZero
    @ApiModelProperty("Piętro")
    private Integer floor;

    @Positive
    @ApiModelProperty("Ilość pomieszczeń")
    private Integer roomCount;

    @Positive
    @ApiModelProperty("Maksymalna liczba najemcó")
    private Integer maxTenants;

    @Positive
    @ApiModelProperty("Metraż")
    private Double area;

    @NotNull
    @ApiModelProperty("Adres biura")
    private AddressDto address;
}

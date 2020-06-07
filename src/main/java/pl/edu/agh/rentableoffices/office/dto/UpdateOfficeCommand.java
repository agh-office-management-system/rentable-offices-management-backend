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

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Zasób reprezentujący żądanie uaktualnienia biura")
public class UpdateOfficeCommand {

    @Positive
    @ApiModelProperty("Nowa liczba pomieszczeń")
    private Integer newRoomCount;

    @Positive
    @ApiModelProperty("Nowy metraż")
    private Double newArea;

    @Positive
    @ApiModelProperty("Nowa maksymalna liczba najemców")
    private Integer newMaxTenants;

    @NotNull
    @ApiModelProperty(value = "Nowy adres", required = true)
    private AddressDto newAddress;
}

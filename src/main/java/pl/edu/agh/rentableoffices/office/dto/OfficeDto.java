package pl.edu.agh.rentableoffices.office.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import pl.edu.agh.rentableoffices.common.AddressDto;

import javax.persistence.Embedded;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Zasób reprezentujący biuro")
public class OfficeDto {

    @PositiveOrZero
    @ApiModelProperty("Piętro")
    private Integer floor;

    @Positive
    @ApiModelProperty("Ilość pomieszczeń")
    private Integer roomCount;

    @Positive
    @ApiModelProperty("Metraż")
    private Double area;

    @Embedded
    @ApiModelProperty("Adres biura")
    private AddressDto address;
}

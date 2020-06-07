package pl.edu.agh.rentableoffices.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel("Zasób reprezentujący adres")
public class AddressDto {
    @NotEmpty
    @ApiModelProperty(value = "Numer mieszkania", example = "23/1", required = true)
    private String number;

    @ApiModelProperty("Ulica")
    private String street;

    @NotEmpty
    @ApiModelProperty(value = "Miejscowość", example = "Kraków", required = true)
    private String city;

    @NotEmpty
    @ApiModelProperty(value = "Kod pocztowy", example = "31-231", required = true)
    private String postalCode;

    @Builder.Default
    @ApiModelProperty(value = "Kraj", example = "PL", notes = "Domyślnie PL")
    private String country = "PL";
}

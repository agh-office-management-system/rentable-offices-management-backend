package pl.edu.agh.rentableoffices.apartment.dto;

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
public class CreateApartmentCommand {
    @PositiveOrZero
    private Integer floor;

    @Positive
    private Integer roomCount;

    @Positive
    private Float area;

    @NotNull
    private AddressDto address;
}

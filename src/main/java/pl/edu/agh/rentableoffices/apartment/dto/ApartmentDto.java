package pl.edu.agh.rentableoffices.apartment.dto;

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
public class ApartmentDto {
    @PositiveOrZero
    private Integer floor;

    @Positive
    private Integer roomCount;

    @Positive
    private Float area;

    @Embedded
    private AddressDto address;
}

package pl.edu.agh.rentableoffices.office.dto;

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
public class UpdateOfficeCommand {

    @Positive
    private Integer newRoomCount;

    @Positive
    private Double newArea;

    @Positive
    private Integer newMaxTenants;

    @NotNull
    private AddressDto newAddress;
}

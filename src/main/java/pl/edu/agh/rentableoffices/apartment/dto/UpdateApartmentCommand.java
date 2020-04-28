package pl.edu.agh.rentableoffices.apartment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.agh.rentableoffices.common.AddressDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateApartmentCommand {

    private Integer newRoomCount;

    private Float newArea;

    private AddressDto newAddress;
}

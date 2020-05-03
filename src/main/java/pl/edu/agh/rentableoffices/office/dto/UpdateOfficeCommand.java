package pl.edu.agh.rentableoffices.office.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.agh.rentableoffices.common.AddressDto;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOfficeCommand {

    private Integer newRoomCount;

    private Float newArea;

    private Integer newMaxTenants;

    private AddressDto newAddress;
}

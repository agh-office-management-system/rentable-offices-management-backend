package pl.edu.agh.rentableoffices.office.mapper;

import org.springframework.stereotype.Component;
import pl.edu.agh.rentableoffices.office.dto.OfficeDto;
import pl.edu.agh.rentableoffices.office.model.Office;
import pl.edu.agh.rentableoffices.common.AbstractMapper;
import pl.edu.agh.rentableoffices.common.AddressDto;

@Component
public class OfficeMapper implements AbstractMapper<Office, OfficeDto> {
    @Override
    public OfficeDto toDto(Office entity) {
        AddressDto addressDto =  AddressDto.builder()
                .city(entity.getAddress().getCity())
                .country(entity.getAddress().getCountry())
                .number(entity.getAddress().getNumber())
                .postalCode(entity.getAddress().getPostalCode())
                .street(entity.getAddress().getStreet())
                .build();

        return OfficeDto.builder()
                .address(addressDto)
                .area(entity.getArea())
                .floor(entity.getFloor())
                .roomCount(entity.getRoomCount())
                .build();
    }
}

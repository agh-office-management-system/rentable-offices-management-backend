package pl.edu.agh.rentableoffices.apartment.mapper;

import org.springframework.stereotype.Component;
import pl.edu.agh.rentableoffices.apartment.dto.ApartmentDto;
import pl.edu.agh.rentableoffices.apartment.model.Apartment;
import pl.edu.agh.rentableoffices.common.AbstractMapper;
import pl.edu.agh.rentableoffices.common.AddressDto;

@Component
public class ApartmentMapper implements AbstractMapper<Apartment, ApartmentDto> {
    @Override
    public ApartmentDto toDto(Apartment entity) {
        AddressDto addressDto =  AddressDto.builder()
                .city(entity.getAddress().getCity())
                .country(entity.getAddress().getCountry())
                .number(entity.getAddress().getNumber())
                .postalCode(entity.getAddress().getPostalCode())
                .street(entity.getAddress().getStreet())
                .build();

        return ApartmentDto.builder()
                .address(addressDto)
                .area(entity.getArea())
                .floor(entity.getFloor())
                .roomCount(entity.getRoomCount())
                .build();
    }
}

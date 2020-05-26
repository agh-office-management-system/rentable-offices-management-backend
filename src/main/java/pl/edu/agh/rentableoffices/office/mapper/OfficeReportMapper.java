package pl.edu.agh.rentableoffices.office.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.agh.rentableoffices.common.AbstractMapper;
import pl.edu.agh.rentableoffices.common.AddressDto;
import pl.edu.agh.rentableoffices.office.dto.OfficeReportDto;
import pl.edu.agh.rentableoffices.office.model.Office;

@Component
@RequiredArgsConstructor
public class OfficeReportMapper implements AbstractMapper<Office, OfficeReportDto> {

    @Override
    public OfficeReportDto toDto(Office entity) {
        AddressDto addressDto =  AddressDto.builder()
                .city(entity.getAddress().getCity())
                .country(entity.getAddress().getCountry())
                .number(entity.getAddress().getNumber())
                .postalCode(entity.getAddress().getPostalCode())
                .street(entity.getAddress().getStreet())
                .build();

        return new OfficeReportDto(entity.getId(), addressDto, entity.getTenantCount());
    }

}

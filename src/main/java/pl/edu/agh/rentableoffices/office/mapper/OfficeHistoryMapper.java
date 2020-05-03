package pl.edu.agh.rentableoffices.office.mapper;

import org.springframework.stereotype.Component;
import pl.edu.agh.rentableoffices.common.AbstractMapper;
import pl.edu.agh.rentableoffices.office.dto.OfficeHistoryDto;
import pl.edu.agh.rentableoffices.office.model.OfficeHistory;

@Component
public class OfficeHistoryMapper implements AbstractMapper<OfficeHistory, OfficeHistoryDto> {

    @Override
    public OfficeHistoryDto toDto(OfficeHistory entity) {
        return new OfficeHistoryDto(entity.getType(), entity.getAdditionalInfo(), entity.getCreatedAt(), entity.getCreatedBy());
    }
}

package pl.edu.agh.rentableoffices.office.dto;

import lombok.*;
import pl.edu.agh.rentableoffices.office.model.OfficeHistoryType;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OfficeHistoryDto {
    private OfficeHistoryType type;

    private String additionalInfo;

    private LocalDateTime createdAt;

    private String createdBy;
}

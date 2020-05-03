package pl.edu.agh.rentableoffices.office.dto;

import lombok.*;
import pl.edu.agh.rentableoffices.common.AddressDto;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OfficeReportDto {
    private Long id;
    private AddressDto addressDto;
    private Integer activeTenants;
}

package pl.edu.agh.rentableoffices.tenant.mapper;

import org.springframework.stereotype.Component;
import pl.edu.agh.rentableoffices.common.AbstractMapper;
import pl.edu.agh.rentableoffices.tenant.dto.TenantDto;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

@Component
public class TenantMapper implements AbstractMapper<Tenant, TenantDto> {
    @Override
    public TenantDto toDto(Tenant entity) {
        return TenantDto.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .isPrivate(entity.isPrivate())
                .email(entity.getEmail())
                .idDocumentNumber(entity.getIdDocumentNumber())
                .idType(entity.getIdType())
                .officeId(entity.getOffice() != null? entity.getOffice().getId() : null)
                .phoneNumber(entity.getPhoneNumber())
                .preferredMeansOfCommunication(entity.getPreferredMeansOfCommunication())
                .rejectedReason(entity.getRejectedReason())
                .status(entity.getStatus())
                .build();
    }
}

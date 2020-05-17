package pl.edu.agh.rentableoffices.tenant.dto;

import lombok.*;
import pl.edu.agh.rentableoffices.office.model.Office;
import pl.edu.agh.rentableoffices.tenant.model.IdType;
import pl.edu.agh.rentableoffices.tenant.model.PreferredMeansOfCommunication;
import pl.edu.agh.rentableoffices.tenant.model.TenantStatus;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TenantDto {
    private String firstName;
    private String lastName;
    private boolean isPrivate;
    private String idDocumentNumber;
    private IdType idType;

    private PreferredMeansOfCommunication preferredMeansOfCommunication;
    private String phoneNumber;
    private String email;

    private TenantStatus status;
    private String rejectedReason;
    private Long officeId;
}

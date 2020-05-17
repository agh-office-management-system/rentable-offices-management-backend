package pl.edu.agh.rentableoffices.tenant.dto;

import lombok.*;
import pl.edu.agh.rentableoffices.tenant.model.IdType;
import pl.edu.agh.rentableoffices.tenant.model.PreferredMeansOfCommunication;
import pl.edu.agh.rentableoffices.tenant.model.TenantStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateTenantCommand {
    private String firstName;
    private String lastName;
    private boolean isPrivate;
    private String idDocumentNumber;
    private IdType idType;

    private PreferredMeansOfCommunication preferredMeansOfCommunication;
    private String phoneNumber;
    private String email;
}

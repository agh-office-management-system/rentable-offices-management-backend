package pl.edu.agh.rentableoffices.tenant.dto;

import lombok.*;
import pl.edu.agh.rentableoffices.tenant.model.IdType;
import pl.edu.agh.rentableoffices.tenant.model.PreferredMeansOfCommunication;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateTenantCommand {
    private String firstName;
    private String lastName;
    private String companyName;
    private Integer numberOfEmployees;
    private String idDocumentNumber;
    private IdType idType;
    private PreferredMeansOfCommunication preferredMeansOfCommunication;
    private String phoneNumber;
    private String email;
}

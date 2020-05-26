package pl.edu.agh.rentableoffices.tenant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.agh.rentableoffices.tenant.model.IdType;
import pl.edu.agh.rentableoffices.tenant.model.PreferredMeansOfCommunication;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateTenantCommand {
    private String firstName;
    private String lastName;
    private String companyName;
    private Integer numberOfEmployees;
    private boolean isPrivate;
    private String idDocumentNumber;
    private IdType idType;

    private PreferredMeansOfCommunication preferredMeansOfCommunication;
    private String phoneNumber;
    private String email;
}

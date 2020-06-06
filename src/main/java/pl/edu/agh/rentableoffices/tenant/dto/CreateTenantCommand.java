package pl.edu.agh.rentableoffices.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("Zasób reprezentujący żądanie utworzenia najemcy")
public class CreateTenantCommand {
    @ApiModelProperty("Imię")
    private String firstName;
    @ApiModelProperty("Nazwisko")
    private String lastName;
    @ApiModelProperty("Nazwa firmy")
    private String companyName;
    @ApiModelProperty("Ilość pracowników")
    private Integer numberOfEmployees;
    @ApiModelProperty("Czy osoba fizyczna?")
    private boolean isPrivate;
    @ApiModelProperty("Numer dokumentu tożsamości")
    private String idDocumentNumber;
    @ApiModelProperty("Rodzaj dokumentu tożsamości")
    private IdType idType;
    @ApiModelProperty("Preferowany kanał komunikacji")
    private PreferredMeansOfCommunication preferredMeansOfCommunication;
    @ApiModelProperty("Numer telefonu")
    private String phoneNumber;
    @ApiModelProperty("Email")
    private String email;
    @ApiModelProperty("Hasło")
    private String password;
}

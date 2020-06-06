package pl.edu.agh.rentableoffices.tenant.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import pl.edu.agh.rentableoffices.tenant.model.IdType;
import pl.edu.agh.rentableoffices.tenant.model.PreferredMeansOfCommunication;
import pl.edu.agh.rentableoffices.tenant.model.TenantStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Zasób reprezenujący najemce")
public class TenantDto {
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
    @ApiModelProperty("Status")
    private TenantStatus status;
    @ApiModelProperty("Powód odrzucenia")
    private String rejectedReason;
    @ApiModelProperty("Id biura")
    private Long officeId;
}

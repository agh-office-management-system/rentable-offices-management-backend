package pl.edu.agh.rentableoffices.incidents.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("Żądanie utworzenia incydentu")
public class CreateIncidentCommand {
    @ApiModelProperty("Temat")
    @NotEmpty
    private String subject;

    @ApiModelProperty("Opis")
    @NotEmpty
    private String description;

    @ApiModelProperty("Twórca")
    private String creator;

    @ApiModelProperty("Data utworzenia")
    private String creationDate;

    @ApiModelProperty("Data ostatniej aktualizacji")
    private String lastUpdated;

    @ApiModelProperty("Status")
    private String status;

    @ApiModelProperty("Priorytet")
    private String priority;

    @ApiModelProperty("Kategoria")
    @NotEmpty
    private String category;

    @ApiModelProperty("Typ")
    @NotEmpty
    private String type;

    @ApiModelProperty("Osoba przypisana")
    private String assignee;
}

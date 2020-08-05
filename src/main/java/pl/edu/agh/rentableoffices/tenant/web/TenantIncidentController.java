package pl.edu.agh.rentableoffices.tenant.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.configuration.SwaggerTags;
import pl.edu.agh.rentableoffices.incidents.dto.CreateIncidentCommand;
import pl.edu.agh.rentableoffices.incidents.service.IncidentService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/tenants", produces = "application/json")
@RequiredArgsConstructor
@Api(value = "Endpointy dla incydentów", tags = SwaggerTags.INCIDENT)
public class TenantIncidentController {
    private final IncidentService incidentService;

    @PostMapping("{id}/incidents")
    @PreAuthorize("hasRole('ROLE_TENANT')")
    @ApiOperation("Stworzenie nowego incydentu")
    public ResponseDto<?> createIncident(@ApiParam("Id najemcy") @PathVariable("id") Long tenantId,
                                            @ApiParam("Żądanie utworzenia nowego incydentu")
                                            @RequestBody @Valid CreateIncidentCommand command) {
        incidentService.createIncident(tenantId, command);
        return ResponseDto.success();
    }

    @GetMapping("{id}/incidents")
    @PreAuthorize("hasRole('ROLE_TENANT')")
    @ApiOperation("Pobranie wszystkich aktywnych i zgłoszonych przez użytkownika incydentów")
    public ResponseDto<?> getUserIncidents(@ApiParam("Id najemcy") @PathVariable("id") Long tenantId) {
        return ResponseDto.success(incidentService.getUserIncidents(tenantId));
    }
}

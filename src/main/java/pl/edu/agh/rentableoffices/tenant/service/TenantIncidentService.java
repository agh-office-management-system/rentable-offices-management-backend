package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import pl.edu.agh.rentableoffices.incidents.dto.CreateIncidentCommand;
import pl.edu.agh.rentableoffices.incidents.service.IncidentService;

@RequiredArgsConstructor
public class TenantIncidentService {
    private final IncidentService incidentService;

    public void createIncident(CreateIncidentCommand command) {
        incidentService.createIncident(tenantId, command);
    }

    public Object getUserIncidents() {
        return null;
    }

    public Object getIncident(Long id) {
        return null;
    }
}

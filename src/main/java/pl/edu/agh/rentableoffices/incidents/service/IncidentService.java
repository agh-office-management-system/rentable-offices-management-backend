package pl.edu.agh.rentableoffices.incidents.service;

import pl.edu.agh.rentableoffices.incidents.dto.CreateIncidentCommand;

import javax.validation.constraints.NotNull;

public class IncidentService {
    public void createIncident(@NotNull Long tenantId, @NotNull CreateIncidentCommand command) {

    }

    public Object getUserIncidents(Long tenantId) {
        return null;
    }

    public Object getIncident(Long id) {
        return null;
    }
}
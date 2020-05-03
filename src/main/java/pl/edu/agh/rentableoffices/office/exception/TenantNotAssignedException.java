package pl.edu.agh.rentableoffices.office.exception;

import pl.edu.agh.rentableoffices.common.BusinessRuntimeException;

import javax.validation.constraints.NotNull;

public class TenantNotAssignedException extends BusinessRuntimeException {
    public TenantNotAssignedException(Long officeId, Long id) {
        super("TENANT_NOT_ASSIGNED_EXCEPTION", new Object[]{id, officeId});
    }
}

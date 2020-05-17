package pl.edu.agh.rentableoffices.office.exception;

import pl.edu.agh.rentableoffices.common.BusinessRuntimeException;

public class TenantAlreadyAssignedException extends BusinessRuntimeException {

    public TenantAlreadyAssignedException(Long id, Long officeId) {
        super("TENANT_ALREADY_ASSIGNED", new Object[]{id, officeId});
    }
}

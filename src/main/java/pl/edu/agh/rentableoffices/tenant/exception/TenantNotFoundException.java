package pl.edu.agh.rentableoffices.tenant.exception;

import pl.edu.agh.rentableoffices.common.BusinessException;

public class TenantNotFoundException extends BusinessException {

    public TenantNotFoundException(Long id) {
        super("TENANT_NOT_FOUND", new Object[] {id});
    }
}

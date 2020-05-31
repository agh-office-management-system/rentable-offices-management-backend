package pl.edu.agh.rentableoffices.tenant.exception;

import pl.edu.agh.rentableoffices.common.BusinessException;

public class LandlordNotFoundException extends BusinessException {

    public LandlordNotFoundException(Long id) {
        super("LANDLORD_NOT_FOUND", new Object[] {id});
    }
}

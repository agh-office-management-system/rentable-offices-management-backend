package pl.edu.agh.rentableoffices.office.exception;

import pl.edu.agh.rentableoffices.common.BusinessException;

public class OfficeNotFoundException extends BusinessException {

    public OfficeNotFoundException(Long id) {
        super("OFFICE_NOT_FOUND", new Object[]{id});
    }
}

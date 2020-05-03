package pl.edu.agh.rentableoffices.office.exception;

import pl.edu.agh.rentableoffices.common.BusinessException;

import javax.validation.constraints.NotNull;

public class OfficeFullException extends BusinessException {
    public OfficeFullException(Long id) {
        super("OFFICE_FULL", new Object[]{id});
    }
}

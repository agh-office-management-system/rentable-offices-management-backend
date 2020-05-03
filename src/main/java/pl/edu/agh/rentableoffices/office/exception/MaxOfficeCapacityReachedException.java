package pl.edu.agh.rentableoffices.office.exception;

import pl.edu.agh.rentableoffices.common.BusinessException;

public class MaxOfficeCapacityReachedException extends BusinessException {

    public MaxOfficeCapacityReachedException(Long id) {
        super("MAX_OFFICE_CAPACITY_REACHED", new Object[]{id});
    }
}

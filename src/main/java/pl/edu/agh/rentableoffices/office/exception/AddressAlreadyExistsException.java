package pl.edu.agh.rentableoffices.office.exception;

import pl.edu.agh.rentableoffices.common.Address;
import pl.edu.agh.rentableoffices.common.BusinessException;


public class AddressAlreadyExistsException extends BusinessException {

    public AddressAlreadyExistsException(Address address) {
        super("ADDRESS_ALREADY_EXISTS", new Object[]{address.getPostalCode(), address.getCity(), address.getStreet(),
        address.getNumber()});
    }
}

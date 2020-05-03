package pl.edu.agh.rentableoffices.messaging.exception;

import pl.edu.agh.rentableoffices.common.BusinessException;

public class MessageNotFound extends BusinessException {
    public MessageNotFound(Long id) {
        super("MESSAGE_NOT_FOUND", new Object[] {id});
    }
}

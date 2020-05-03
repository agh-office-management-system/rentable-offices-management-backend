package pl.edu.agh.rentableoffices.messaging.exception;

import pl.edu.agh.rentableoffices.common.BusinessException;

public class ReceiverNotFound extends BusinessException {

    public ReceiverNotFound(String receiver) {
        super("RECEIVER_NOT_FOUND", new Object[]{receiver});
    }
}

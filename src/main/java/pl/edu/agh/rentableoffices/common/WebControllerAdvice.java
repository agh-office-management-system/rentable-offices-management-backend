package pl.edu.agh.rentableoffices.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Locale;

//TODO locale
//TODO i18n + Spisanie kodów błedów
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class WebControllerAdvice {
    private final MessageSource messageSource;

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseDto<Void> handleAccessDenied(AccessDeniedException exception) {
        return ResponseDto.error("ACCESS_DENIED");
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseDto<Void> handleIllegalState(IllegalStateException exception) {
        return ResponseDto.error("ILLEGAL_STATE");
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseDto<Void> handleIllegalArgument(IllegalArgumentException exception) {
        return ResponseDto.error("ILLEGAL_ARGUMENT");
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseDto<Void> handleBusinessException(BusinessException exception) {
        String message = messageSource.getMessage(exception.getCode(), exception.getParams(), Locale.getDefault());
        return ResponseDto.error(exception.getCode(), message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<Void> illegalStateException(Exception exception) {
        log.error("Unexpected exception occured", exception);
        String message = messageSource.getMessage("UNEXPECTED_EXCEPTION", new Object[]{}, Locale.getDefault());
        return ResponseDto.error("UNEXPECTED_EXCEPTION", message);
    }

}
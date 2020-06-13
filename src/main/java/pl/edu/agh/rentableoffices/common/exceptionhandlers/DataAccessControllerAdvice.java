package pl.edu.agh.rentableoffices.common.exceptionhandlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.edu.agh.rentableoffices.common.ResponseDto;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class DataAccessControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseDto<Void> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        return ResponseDto.error("ILLEGAL_ARGUMENT", exception.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseDto<Void> handleDataAccessException(DataAccessException exception) {
        return ResponseDto.error("ILLEGAL_ARGUMENT", exception.getMessage());
    }
}

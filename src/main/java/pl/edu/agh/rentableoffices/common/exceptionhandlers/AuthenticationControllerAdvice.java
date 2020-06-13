package pl.edu.agh.rentableoffices.common.exceptionhandlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.edu.agh.rentableoffices.common.ResponseDto;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class AuthenticationControllerAdvice {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseDto<Void> handleUsernameNotFound(UsernameNotFoundException exception) {
        return ResponseDto.error("USERNAME_NOT_FOUND", "User with given login not exist");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ResponseDto<Void> handleAccessDenied(AccessDeniedException exception) {
        return ResponseDto.error("ACCESS_DENIED", exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseDto<Void> handleAuthenticationException(AuthenticationException exception) {
        return ResponseDto.error("AUTHENTICATION_EXCEPTION", "Error during authentication. Reason " + exception.getMessage());
    }
}

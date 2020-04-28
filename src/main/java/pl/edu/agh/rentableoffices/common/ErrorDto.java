package pl.edu.agh.rentableoffices.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorDto {
    private String code;
    private String message;

    public static ErrorDto of(String code) {
        return new ErrorDto(code, null);
    }

    public static ErrorDto of(String code, String message) {
        return new ErrorDto(code, message);
    }
}

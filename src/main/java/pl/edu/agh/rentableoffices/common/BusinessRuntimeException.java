package pl.edu.agh.rentableoffices.common;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class BusinessRuntimeException extends RuntimeException {
    @NotNull
    private final String code;
    private Object[] params;

    public BusinessRuntimeException(@NotNull String code) {
        super();
        this.code = code;
    }

    public BusinessRuntimeException(@NotNull String code, Object[] params) {
        super();
        this.code = code;
        this.params = params;
    }
}

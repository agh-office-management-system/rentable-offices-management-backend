package pl.edu.agh.rentableoffices.common;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class BusinessException extends Exception {
    @NotNull
    private final String code;
    private final Object[] params;

    public BusinessException(@NotNull String code) {
        super();
        this.code = code;
        this.params = null;
    }

    public BusinessException(@NotNull String code, Object[] params) {
        super();
        this.code = code;
        this.params = params;
    }
}

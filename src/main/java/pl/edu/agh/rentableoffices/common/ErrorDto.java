package pl.edu.agh.rentableoffices.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ApiModel("Błąd")
public class ErrorDto {
    @ApiModelProperty("Kod błędu")
    private String code;
    @ApiModelProperty("Tłumaczenie błedu")
    private String message;

    public static ErrorDto of(String code) {
        return new ErrorDto(code, null);
    }

    public static ErrorDto of(String code, String message) {
        return new ErrorDto(code, message);
    }
}

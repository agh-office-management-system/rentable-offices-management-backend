package pl.edu.agh.rentableoffices.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ApiModel("Zasób bazowy reprezentujący odpowiedź systemu")
public class ResponseDto<T> {
    @ApiModelProperty("Odpowiedź (Jeżeli żądanie zakończyło się sukcesem)")
    private T response;
    @ApiModelProperty("Błąd (Jeżeli żądanie zakończyło się niepowodzeniem)")
    private ErrorDto error;
    @ApiModelProperty("Czy żądanie zakończyło się sukcesem")
    private boolean isSuccess;

    public static ResponseDto<Void> success() {
        return new ResponseDto<>(null, null, true);
    }

    public static <T> ResponseDto<T> success(T response) {
        return new ResponseDto<>(response, null, true);
    }

    public static <T> ResponseDto<T> error(String code) {
        return new ResponseDto<>(null, ErrorDto.of(code), false);
    }

    public static <T> ResponseDto<T> error(String code, String message) {
        return new ResponseDto<>(null, ErrorDto.of(code, message), false);
    }
}

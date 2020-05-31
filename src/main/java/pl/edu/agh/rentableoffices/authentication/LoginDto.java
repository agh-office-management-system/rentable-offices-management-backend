package pl.edu.agh.rentableoffices.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDto {
    private String token;
    private PrincipalDto principalDto;
}

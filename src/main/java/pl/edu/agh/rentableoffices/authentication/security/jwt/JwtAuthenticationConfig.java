package pl.edu.agh.rentableoffices.authentication.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtAuthenticationConfig {
    private int expiration;
    private String secret;
}

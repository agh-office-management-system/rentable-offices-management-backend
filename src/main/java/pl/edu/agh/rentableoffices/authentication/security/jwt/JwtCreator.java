package pl.edu.agh.rentableoffices.authentication.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import pl.edu.agh.rentableoffices.authentication.security.AppPrincipal;

import java.time.Instant;
import java.util.Date;


public class JwtCreator {

    private final JwtAuthenticationConfig authenticationConfig;

    public JwtCreator(JwtAuthenticationConfig authenticationConfig) {
        this.authenticationConfig = authenticationConfig;
    }

    public String createJwt(Authentication authentication) {
        AppPrincipal principal = (AppPrincipal) authentication.getPrincipal();
        Instant now = Instant.now();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuer("RentableOffices")
                .claim("role", principal.getAuthorities().iterator().next().getAuthority())
                .claim("id", principal.getId())
                .claim("fullName", principal.getFullName())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(authenticationConfig.getExpiration())))
                .signWith(SignatureAlgorithm.HS256, authenticationConfig.getSecret().getBytes())
                .compact();
    }
}

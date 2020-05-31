package pl.edu.agh.rentableoffices.authentication.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.authentication.security.AppPrincipal;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtCreator {
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
                .setExpiration(Date.from(now.plusSeconds(JwtAuthenticationConfig.getExpiration())))
                .signWith(SignatureAlgorithm.HS256, JwtAuthenticationConfig.getSecret().getBytes())
                .compact();
    }
}

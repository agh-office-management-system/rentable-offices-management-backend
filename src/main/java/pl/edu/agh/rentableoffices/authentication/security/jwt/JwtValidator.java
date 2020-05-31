package pl.edu.agh.rentableoffices.authentication.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.agh.rentableoffices.authentication.security.AppPrincipal;

import javax.servlet.http.HttpServletRequest;

public class JwtValidator {
    private static final String TOKEN_PREFIX = "Bearer ";

    public String getTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            return authHeader.substring(TOKEN_PREFIX.length());
        }
        return "";
    }

    public AppPrincipal authenticate(String token) {
        Claims claims = getClaimsFromToken(token);
        String email = claims.getSubject();
        String fullName = claims.get("fullName", String.class);
        String role = claims.get("role", String.class);
        Long id = claims.get("id", Long.class);

        UserDetails tokenUserDetails = User.builder()
                .username(email)
                .password(token)
                .authorities(role)
                .build();

        return new AppPrincipal(id, fullName, tokenUserDetails);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JwtAuthenticationConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}

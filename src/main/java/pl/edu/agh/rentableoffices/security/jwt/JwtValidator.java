package pl.edu.agh.rentableoffices.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import pl.edu.agh.rentableoffices.security.LandlordPrincipal;
import pl.edu.agh.rentableoffices.security.Roles;
import pl.edu.agh.rentableoffices.security.TenantPrincipal;

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

    public TenantPrincipal authenticateTenant(String token) {
        Claims claims = getClaimsFromToken(token);
        String principalName = claims.getSubject();
        String role = claims.get("roles", String.class);
        return new TenantPrincipal(principalName, Roles.valueOf(role));
    }

    public LandlordPrincipal authenticateLandlord(String token) {
        Claims claims = getClaimsFromToken(token);
        String principalName = claims.getSubject();
        String role = claims.get("roles", String.class);
        return new LandlordPrincipal(principalName, Roles.valueOf(role));
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(JwtAuthenticationConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}

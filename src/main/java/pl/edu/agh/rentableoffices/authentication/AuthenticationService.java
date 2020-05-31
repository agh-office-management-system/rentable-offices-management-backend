package pl.edu.agh.rentableoffices.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Base64Utils;
import pl.edu.agh.rentableoffices.authentication.security.AppPrincipal;
import pl.edu.agh.rentableoffices.authentication.security.jwt.JwtCreator;


@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtCreator jwtCreator;
    private final AuthenticationManager tenantManager;
    private final AuthenticationManager landlordManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginDto loginTenant(String authHeader) {
        if (hasAuthenticationHeader(authHeader)) {
            UsernamePasswordAuthenticationToken authToken = getAuthToken(authHeader);
            Authentication authentication = tenantManager.authenticate(authToken);
            return createLoginDto(authentication);
        }
        throw new AuthenticationServiceException("No authentication header");
    }

    public LoginDto loginLandlord(String authHeader) {
        if (hasAuthenticationHeader(authHeader)) {
            UsernamePasswordAuthenticationToken authToken = getAuthToken(authHeader);
            Authentication authentication = landlordManager.authenticate(authToken);
            return createLoginDto(authentication);
        }
        throw new AuthenticationServiceException("No authentication header");
    }

    private UsernamePasswordAuthenticationToken getAuthToken(String authHeader) {
        String[] credentials = getCredentialsFromAuthenticationHeader(authHeader);
        return new UsernamePasswordAuthenticationToken(credentials[0], credentials[1]);
    }

    private boolean hasAuthenticationHeader(String authHeader) {
        return authHeader != null && !authHeader.isEmpty();
    }

    private String[] getCredentialsFromAuthenticationHeader(String authHeader) {
        String decodedHeader = getDecodedHeader(authHeader);
        return getUsernameAndPassword(decodedHeader);
    }

    private String getDecodedHeader(String authHeader) {
        if (!authHeader.startsWith("Basic ")) {
            throw new IllegalArgumentException("Invalid authentication header");
        }
        byte[] base64Credentials = authHeader.substring(6).getBytes();
        byte[] decoded;

        try {
            decoded = Base64Utils.decode(base64Credentials);
        } catch (IllegalArgumentException e) {
            throw new AuthenticationServiceException("Failed to decode basic authentication header");
        }
        return new String(decoded);
    }

    private String[] getUsernameAndPassword(String decodedHeader) {
        int delim = decodedHeader.indexOf(":");
        if (delim == -1) {
            throw new AuthenticationServiceException("Invalid basic authentication token");
        } else {
            return new String[]{decodedHeader.substring(0, delim), decodedHeader.substring(delim + 1)};
        }
    }

    private LoginDto createLoginDto(Authentication auth) {
        String token = jwtCreator.createJwt(auth);
        return new LoginDto(token, new PrincipalDto((AppPrincipal) auth.getPrincipal()));
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}

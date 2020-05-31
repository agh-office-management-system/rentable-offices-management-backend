package pl.edu.agh.rentableoffices.authentication.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHelper {

    public static AppPrincipal getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AppPrincipal) {
            return (AppPrincipal) principal;
        }
        throw new AuthenticationServiceException("Invalid principal. Tenant principal expected");
    }

    public static String getCurrentUsername() {
        return null;
    }
}

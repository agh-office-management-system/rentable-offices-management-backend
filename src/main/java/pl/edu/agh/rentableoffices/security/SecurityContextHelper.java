package pl.edu.agh.rentableoffices.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHelper {

    public static TenantPrincipal getTenantPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof TenantPrincipal) {
            return (TenantPrincipal) principal;
        }
        throw new AuthenticationServiceException("Invalid principal. Tenant principal expected");
    }

    public static LandlordPrincipal getLandlordPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof LandlordPrincipal) {
            return (LandlordPrincipal) principal;
        }
        throw new AuthenticationServiceException("Invalid principal. Landlord principal expected");
    }

    public static String getCurrentUsername() {
        return null;
    }
}

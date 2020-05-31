package pl.edu.agh.rentableoffices.security.filters;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.edu.agh.rentableoffices.security.TenantPrincipal;
import pl.edu.agh.rentableoffices.security.jwt.JwtValidator;
import pl.edu.agh.rentableoffices.security.jwt.TokenBasedAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TenantsTokenAuthenticationFilter extends OncePerRequestFilter {
    private final static Log LOGGER = LogFactory.getLog(TenantsTokenAuthenticationFilter.class);

    private final JwtValidator jwtValidator;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public TenantsTokenAuthenticationFilter(JwtValidator jwtValidator, AuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtValidator = jwtValidator;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String token = jwtValidator.getTokenFromRequest(request);
        if (shouldFilter(token, request.getRequestURI())) {
            authenticateWithToken(token, request, response);
        }
        chain.doFilter(request, response);
    }

    private boolean shouldFilter(String token, String requestURI) {
        return !token.isEmpty() && "api/tenants/login".equals(requestURI);
    }

    private void authenticateWithToken(String token, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            TenantPrincipal principal = jwtValidator.authenticateTenant(token);
            SecurityContextHolder.getContext().setAuthentication(
                    new TokenBasedAuthentication(principal, token, principal.grantedAuthorities()));
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, e);
        }
    }
}
package pl.edu.agh.rentableoffices.authentication.security.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.edu.agh.rentableoffices.authentication.security.AppPrincipal;
import pl.edu.agh.rentableoffices.authentication.security.jwt.JwtValidator;
import pl.edu.agh.rentableoffices.authentication.security.jwt.TokenBasedAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtValidator jwtValidator;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (shouldFilter(token)) {
            authenticateWithToken(token, request, response);
        }
        chain.doFilter(request, response);
    }

    private boolean shouldFilter(String token) {
        return !token.isEmpty();
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        return jwtValidator.getTokenFromRequest(request);
    }

    private void authenticateWithToken(String token, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            AppPrincipal principal = jwtValidator.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(
                    new TokenBasedAuthentication(principal, token, principal.getAuthorities()));
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            authenticationEntryPoint.commence(request, response, e);
        }
    }
}
package pl.edu.agh.rentableoffices.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import pl.edu.agh.rentableoffices.security.RestAccessDeniedHandler;
import pl.edu.agh.rentableoffices.security.RestAuthenticationEntryPoint;
import pl.edu.agh.rentableoffices.security.filters.LandlordsTokenAuthenticationFilter;
import pl.edu.agh.rentableoffices.security.filters.TenantsTokenAuthenticationFilter;
import pl.edu.agh.rentableoffices.security.jwt.JwtValidator;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationEntryPoint authenticationEntryPoint = new RestAuthenticationEntryPoint();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(new RestAccessDeniedHandler());

        JwtValidator jwtValidator = new JwtValidator();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/tenants/login", "/landlords/login").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new LandlordsTokenAuthenticationFilter(jwtValidator, authenticationEntryPoint),
                        BasicAuthenticationFilter.class)
                .addFilterBefore(new TenantsTokenAuthenticationFilter(jwtValidator, authenticationEntryPoint),
                        BasicAuthenticationFilter.class);

        http.csrf().disable();
        http.cors().configurationSource(request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
            corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
            corsConfiguration.addAllowedMethod(HttpMethod.POST);
            corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
            corsConfiguration.addAllowedMethod(HttpMethod.PUT);
            return corsConfiguration;
        });
    }
}

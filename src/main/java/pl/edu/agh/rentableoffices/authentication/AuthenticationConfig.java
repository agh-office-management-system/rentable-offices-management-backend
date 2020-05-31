package pl.edu.agh.rentableoffices.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.agh.rentableoffices.authentication.security.jwt.JwtCreator;
import pl.edu.agh.rentableoffices.landlord.dao.LandlordRepository;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;

import java.util.Collections;

@Configuration
public class AuthenticationConfig {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public AuthenticationService authenticationServiceBean(TenantRepository tenantRepository,
                                                           LandlordRepository landlordRepository) {
        JwtCreator jwtCreator = new JwtCreator();
        AuthenticationManager tenantsAuthManager = getTenantsAuthManager(tenantRepository);
        AuthenticationManager landlordsAuthManager = getLandlordsAuthManager(landlordRepository);
        return new AuthenticationService(jwtCreator, tenantsAuthManager, landlordsAuthManager, passwordEncoder);
    }

    private AuthenticationManager getTenantsAuthManager(TenantRepository tenantRepository) {
        DaoAuthenticationProvider o = new DaoAuthenticationProvider();
        o.setPasswordEncoder(passwordEncoder);
        o.setUserDetailsService(new TenantsDetailService(tenantRepository));
        return new ProviderManager(Collections.singletonList(o));
    }

    private AuthenticationManager getLandlordsAuthManager(LandlordRepository landlordRepository) {
        DaoAuthenticationProvider o = new DaoAuthenticationProvider();
        o.setPasswordEncoder(passwordEncoder);
        o.setUserDetailsService(new LandlordsDetailService(landlordRepository));
        return new ProviderManager(Collections.singletonList(o));
    }
}

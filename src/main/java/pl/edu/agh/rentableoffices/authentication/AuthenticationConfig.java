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
    public AuthenticationService authenticationServiceBean(JwtCreator jwtCreator,
                                                           TenantRepository tenantRepository,
                                                           LandlordRepository landlordRepository) {
        AuthenticationManager tenantsAuthManager = getTenantsAuthManager(tenantRepository);
        AuthenticationManager landlordsAuthManager = getLandlordsAuthManager(landlordRepository);
        return new AuthenticationService(jwtCreator, tenantsAuthManager, landlordsAuthManager, passwordEncoder);
    }

    private AuthenticationManager getTenantsAuthManager(TenantRepository tenantRepository) {
        DaoAuthenticationProvider provider = initDaoAuthenticationProvider();
        provider.setUserDetailsService(new TenantsDetailService(tenantRepository));
        return new ProviderManager(Collections.singletonList(provider));
    }

    private AuthenticationManager getLandlordsAuthManager(LandlordRepository landlordRepository) {
        DaoAuthenticationProvider provider = initDaoAuthenticationProvider();
        provider.setUserDetailsService(new LandlordsDetailService(landlordRepository));
        return new ProviderManager(Collections.singletonList(provider));
    }

    private DaoAuthenticationProvider initDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }
}

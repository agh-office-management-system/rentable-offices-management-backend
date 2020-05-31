package pl.edu.agh.rentableoffices.authentication;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.authentication.security.AppPrincipal;
import pl.edu.agh.rentableoffices.authentication.security.Roles;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

@Service
@AllArgsConstructor
public class TenantsDetailService implements UserDetailsService {
    private final TenantRepository tenantRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Tenant tenant = tenantRepository.getByEmail(email);
        if (tenant == null) {
            throw new UsernameNotFoundException("Tenant with email " + email + " not found");
        }
        UserDetails userDetails = User.builder()
                .username(tenant.getEmail())
                .password(tenant.getPassword())
                .authorities(Roles.TENANT.name())
                .build();

        return new AppPrincipal(tenant.getId(), tenant.getFullName(), userDetails);
    }
}

package pl.edu.agh.rentableoffices.security;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class TenantPrincipal {

    private String name;
    private Roles role;

    public Collection<? extends GrantedAuthority> grantedAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
}

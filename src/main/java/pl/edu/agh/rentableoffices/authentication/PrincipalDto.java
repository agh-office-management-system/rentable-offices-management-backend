package pl.edu.agh.rentableoffices.authentication;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import pl.edu.agh.rentableoffices.authentication.security.AppPrincipal;


@Getter
public class PrincipalDto {
    private Long id;
    private String email;
    private String fullName;
    private String role;

    PrincipalDto(AppPrincipal principal) {
        id = principal.getId();
        email = principal.getUsername();
        fullName = principal.getFullName();
        GrantedAuthority authority = principal.getAuthorities().iterator().next();
        role = authority.getAuthority();
    }
}

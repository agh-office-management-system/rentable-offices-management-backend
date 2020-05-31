package pl.edu.agh.rentableoffices.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.authentication.security.AppPrincipal;
import pl.edu.agh.rentableoffices.landlord.dao.LandlordRepository;
import pl.edu.agh.rentableoffices.landlord.model.Landlord;


@Service
@RequiredArgsConstructor
public class LandlordsDetailService implements UserDetailsService {

    private final LandlordRepository landlordRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Landlord landlord = landlordRepository.getByEmail(email);
        if (landlord == null) {
            throw new UsernameNotFoundException("Landlord with email " + email + " not found");
        }
        UserDetails userDetails = User.builder()
                .username(landlord.getEmail())
                .password(landlord.getPassword())
                .authorities(landlord.getRole().name())
                .build();

        return new AppPrincipal(landlord.getId(), landlord.getFullName(), userDetails);
    }
}

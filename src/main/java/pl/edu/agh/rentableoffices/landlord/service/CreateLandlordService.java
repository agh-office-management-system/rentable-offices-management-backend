package pl.edu.agh.rentableoffices.landlord.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.authentication.AuthenticationService;
import pl.edu.agh.rentableoffices.authentication.security.Roles;
import pl.edu.agh.rentableoffices.landlord.dao.LandlordRepository;
import pl.edu.agh.rentableoffices.landlord.dto.CreateLandlordCommand;
import pl.edu.agh.rentableoffices.landlord.model.Landlord;
import pl.edu.agh.rentableoffices.landlord.model.LandlordBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateLandlordService {

    private final AuthenticationService authenticationService;
    private final LandlordRepository landlordRepository;

    public Long create(CreateLandlordCommand command) {
        Landlord landlord = LandlordBuilder.newLandlord(command.getFirstName(), command.getLastName())
                .email(command.getEmail())
                .phone(command.getPhoneNumber())
                .role(Roles.valueOf(command.getRole().toUpperCase()))
                .password(authenticationService.encodePassword(command.getPassword()))
                .build();

        landlord = landlordRepository.save(landlord);
        log.info("Landlord {} created", landlord.getFullName());
        return landlord.getId();
    }
}

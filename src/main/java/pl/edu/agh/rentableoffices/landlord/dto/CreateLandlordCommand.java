package pl.edu.agh.rentableoffices.landlord.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateLandlordCommand {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    private String password;
    private String role;
}

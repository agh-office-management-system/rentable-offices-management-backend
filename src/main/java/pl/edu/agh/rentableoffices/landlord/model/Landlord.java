package pl.edu.agh.rentableoffices.landlord.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.authentication.security.Roles;
import pl.edu.agh.rentableoffices.common.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Landlord")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Landlord extends EntityBase {

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Roles role;

    @Column(name = "password", nullable = false)
    @NotEmpty
    private String password;


    public String getFullName() {
        return firstName + " " + lastName + " (" + email + ")";
    }
}
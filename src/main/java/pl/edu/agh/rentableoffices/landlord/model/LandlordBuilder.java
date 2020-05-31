package pl.edu.agh.rentableoffices.landlord.model;

import pl.edu.agh.rentableoffices.authentication.security.Roles;

public class LandlordBuilder {
    private String phoneNumber;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Roles role;

    public static LandlordBuilder newLandlord(String firstName, String lastName) {
        LandlordBuilder builder = new LandlordBuilder();
        builder.firstName = firstName;
        builder.lastName = lastName;
        return builder;
    }

    public LandlordBuilder phone(String phone) {
        this.phoneNumber = phone;
        return this;
    }

    public LandlordBuilder email(String email) {
        this.email = email;
        return this;
    }

    public LandlordBuilder password(String password) {
        this.password = password;
        return this;
    }

    public LandlordBuilder role(Roles role) {
        this.role = role;
        return this;
    }

    public Landlord build() {
        return new Landlord(email, firstName, lastName, phoneNumber, role, password);
    }
}

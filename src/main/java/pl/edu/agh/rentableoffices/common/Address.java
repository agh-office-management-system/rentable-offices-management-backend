package pl.edu.agh.rentableoffices.common;

import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Address {

    @Column(name = "number")
    private String number;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postalCode")
    private String postalCode;

    @Column(name = "country")
    private String country;

    public static Address fromDto(AddressDto dto) {
        return new Address(dto.getNumber(), dto.getStreet(), dto.getCity(), dto.getPostalCode(), dto.getCity());
    }

    public void update(AddressDto dto) {
        this.postalCode = dto.getPostalCode();
        this.city = dto.getCity();
        this.number = dto.getNumber();
        this.street = dto.getStreet();
        this.country = dto.getCountry();
    }

    @Override
    public String toString() {
        return street + " " + number + ", " + postalCode + " " + city;
    }
}

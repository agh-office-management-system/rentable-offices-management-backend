package pl.edu.agh.rentableoffices.common;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getNumber(), address.getNumber()) &&
                Objects.equals(getStreet(), address.getStreet()) &&
                Objects.equals(getCity(), address.getCity()) &&
                Objects.equals(getPostalCode(), address.getPostalCode()) &&
                Objects.equals(getCountry(), address.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getStreet(), getCity(), getPostalCode(), getCountry());
    }
}

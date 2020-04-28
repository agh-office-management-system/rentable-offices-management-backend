package pl.edu.agh.rentableoffices.apartment.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.common.Address;
import pl.edu.agh.rentableoffices.common.AddressDto;
import pl.edu.agh.rentableoffices.common.EntityBase;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * Apartment entity
 */
@Entity
@Table(name = "Apartment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Apartment extends EntityBase {

    @PositiveOrZero
    private Integer floor;

    @Positive
    private Integer roomCount;

    @Positive
    private Float area;

    @Embedded
    private Address address;

    //TODO list of tenants ?
    //TODO Room entity ?

    public static Apartment create(Integer floor, Integer roomCount, Float area, Address address) {
        return new Apartment(floor, roomCount, area, address);
    }

    public void update(Integer newRoomCount, Float newArea, AddressDto newAddress) {
        //TODO validation other than != null
        if(newRoomCount != null) {
            this.roomCount = newRoomCount;
        }
        if(newArea != null) {
            this.area = newArea;
        }
        if(newAddress != null ) {
            this.address.update(newAddress);
        }
    }
}

package pl.edu.agh.rentableoffices.office.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.common.Address;
import pl.edu.agh.rentableoffices.common.AddressDto;
import pl.edu.agh.rentableoffices.common.EntityBase;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "Apartment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Office extends EntityBase {

    @PositiveOrZero
    private Integer floor;

    @Positive
    private Integer roomCount;

    @Positive
    private Float area;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private List<Tenant> tenants;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfficeHistory> history;

    public static Office create(Integer floor, Integer roomCount, Float area, Address address) {
        OfficeHistory history = OfficeHistory.created();
        return new Office(floor, roomCount, area, address, Collections.emptyList(), List.of(history));
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
        OfficeHistory history = OfficeHistory.updated();
        this.history.add(history);
    }

    public void assignTenant(Tenant tenant) {
        this.tenants.add(tenant);
        OfficeHistory history = OfficeHistory.tenantAssigned();
        this.history.add(history);
    }

    public void removeTenant(Tenant tenant) {
        this.tenants.remove(tenant);
        OfficeHistory history = OfficeHistory.tenantRemoved();
        this.history.add(history);
    }

    public void completeRepair() {
        OfficeHistory history = OfficeHistory.repairCompleted();
        this.history.add(history);
    }
}

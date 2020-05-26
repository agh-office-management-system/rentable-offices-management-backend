package pl.edu.agh.rentableoffices.office.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.common.Address;
import pl.edu.agh.rentableoffices.common.AddressDto;
import pl.edu.agh.rentableoffices.common.BusinessRuntimeException;
import pl.edu.agh.rentableoffices.common.EntityBase;
import pl.edu.agh.rentableoffices.office.exception.MaxOfficeCapacityReachedException;
import pl.edu.agh.rentableoffices.office.exception.TenantAlreadyAssignedException;
import pl.edu.agh.rentableoffices.office.exception.TenantNotAssignedException;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
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
    private Double area;

    @Embedded
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tenant> tenants;

    @Positive
    private Integer maxTenants;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfficeHistory> history;

    public static Office create(Integer floor, Integer roomCount, Double area, Integer maxTenants, Address address) {
        OfficeHistory history = OfficeHistory.created();
        return new Office(floor, roomCount, area, address, Collections.emptyList(), maxTenants, Arrays.asList(history));
    }

    public void update(Integer newRoomCount, Double newArea, Integer maxTenants, AddressDto newAddress) {
        if(newRoomCount != null) {
            this.roomCount = newRoomCount;
        }
        if(newArea != null) {
            this.area = newArea;
        }
        if(maxTenants != null) {
            if(getTenantCount() > maxTenants) {
                throw new BusinessRuntimeException("MAX_TENANTS_LESS_THAN_ACTIVE_TENANTS");
            }
            this.maxTenants = maxTenants;
        }
        if(newAddress != null ) {
            this.address.update(newAddress);
        }
        OfficeHistory event = OfficeHistory.updated();
        this.history.add(event);
    }

    public void assignTenant(Tenant tenant) throws MaxOfficeCapacityReachedException {
        if(tenants.stream().anyMatch(t -> t.getId().equals(getId()))) {
            throw new TenantAlreadyAssignedException(tenant.getId(), getId());
        }
        if(maxTenants != null && tenants.size() == maxTenants) {
            throw new MaxOfficeCapacityReachedException(this.getId());
        }
        this.tenants.add(tenant);
        OfficeHistory event = OfficeHistory.tenantAssigned();
        this.history.add(event);
    }

    public void removeTenant(Tenant tenant) {
        if(!this.tenants.contains(tenant)) {
            throw new TenantNotAssignedException(this.getId(), tenant.getId());
        }
        this.tenants.remove(tenant);
        OfficeHistory event = OfficeHistory.tenantRemoved();
        this.history.add(event);
    }

    public Integer getTenantCount() {
        if(tenants == null) {
            return 0;
        }
        return tenants.stream()
                .mapToInt(t -> t.isPrivate() ? 1 : t.getNumberOfEmployees())
                .sum();
    }

    public void completeRepair() {
        OfficeHistory event = OfficeHistory.repairCompleted();
        this.history.add(event);
    }


}

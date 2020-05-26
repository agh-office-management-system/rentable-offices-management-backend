package pl.edu.agh.rentableoffices.tenant.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.common.EntityBase;
import pl.edu.agh.rentableoffices.office.exception.MaxOfficeCapacityReachedException;
import pl.edu.agh.rentableoffices.office.model.Office;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

@Entity
@Table(name = "Tenant")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Tenant extends EntityBase {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "number_of_employees")
    private Integer numberOfEmployees;

    @Column(name = "is_private")
    private boolean isPrivate;

    @Column(name = "id_document_number")
    private String idDocumentNumber;

    @Column(name = "id_type")
    private IdType idType;

    @Column(name = "preferred_mean_of_communication")
    private PreferredMeansOfCommunication preferredMeansOfCommunication;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    @NotEmpty
    private String email;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private TenantStatus status;

    @Column(name = "rejectedReason")
    private String rejectedReason;

    @ManyToOne
    private Office office;

    public void update( String firstName, String lastName, String companyName, Integer numberOfEmployees, IdType idType, String idDocumentNumber,
                       PreferredMeansOfCommunication preferredMeansOfCommunication, String phoneNumber, String email) throws MaxOfficeCapacityReachedException {
        if(isPrivate) {
            this.firstName = firstName;
            this.lastName = lastName;
        } else {
            this.companyName = companyName;
            if(office != null && (office.getTenantCount()-this.numberOfEmployees + numberOfEmployees > office.getMaxTenants())) {
                throw new MaxOfficeCapacityReachedException(office.getId());
            }
            this.numberOfEmployees = numberOfEmployees;
        }

        this.idDocumentNumber = idDocumentNumber;
        this.idType = idType;
        this.preferredMeansOfCommunication = preferredMeansOfCommunication;
        this.phoneNumber = phoneNumber;
        //TODO -> Remove email or migrate messages/notification
        this.email = email;
    }

    public void verify() {
        checkStatus(() -> status != TenantStatus.CREATED && status != TenantStatus.CORRECTED);
        this.status = TenantStatus.VERIFIED;
    }

    public void reject(String reason) {
        checkStatus(() -> status != TenantStatus.CREATED && status != TenantStatus.CORRECTED);
        this.status = TenantStatus.REJECTED;
        this.rejectedReason = reason;
    }

    public void resubmit() {
        checkStatus(() -> status != TenantStatus.REJECTED);
        this.status = TenantStatus.CORRECTED;
        this.rejectedReason = null;
    }

    public String getFullName() {
        return isPrivate
                ? firstName + " " + lastName + " (" + email +")"
                : companyName + " (" + email +")";
    }

    private void checkStatus(BooleanSupplier statusFunction) {
        if(statusFunction.getAsBoolean()) {
            throw new IllegalStateException("INVALID_TENANT_STATUS");
        }
    }
}

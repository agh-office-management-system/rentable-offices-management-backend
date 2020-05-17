package pl.edu.agh.rentableoffices.tenant.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.common.EntityBase;
import pl.edu.agh.rentableoffices.office.model.Office;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Tenant")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tenant extends EntityBase {
    private String firstName;
    private String lastName;
    private boolean isPrivate;
    private String idDocumentNumber;
    private IdType idType;

    private PreferredMeansOfCommunication preferredMeansOfCommunication;
    private String phoneNumber;
    @NotEmpty
    private String email;

    private TenantStatus status;
    private String rejectedReason;

    @ManyToOne
    private Office office;
    public static Tenant create(
            String firstName, String lastName, boolean isPrivate, IdType idType, String idDocumentNumber,
            PreferredMeansOfCommunication preferredMeansOfCommunication, String phoneNumber, String email) {
        return new Tenant(firstName, lastName, isPrivate, idDocumentNumber, idType, preferredMeansOfCommunication,
                phoneNumber, email, TenantStatus.CREATED, null,  null);
    }

    public void verify() {
        if(status != TenantStatus.CREATED) {
            throw new IllegalStateException("INVALID_TENANT_STATUS");
        }
        this.status = TenantStatus.VERIFIED;
    }

    public void reject(String reason) {
        if(status != TenantStatus.CREATED) {
            throw new IllegalStateException("INVALID_TENANT_STATUS");
        }
        this.status = TenantStatus.REJECTED;
        this.rejectedReason = reason;
    }

    public String getFullName() {
        return firstName + " " + lastName + " (" + email +")";
    }
}

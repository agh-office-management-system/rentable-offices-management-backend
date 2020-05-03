package pl.edu.agh.rentableoffices.tenant.model;

import lombok.Getter;
import pl.edu.agh.rentableoffices.common.EntityBase;
import pl.edu.agh.rentableoffices.office.model.Office;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Tenant extends EntityBase {
    private String firstName;
    private String lastName;
    private boolean isPrivate;
    private String idDocumentNumber;
    private IdType idType;

    private PreferredMeansOfCommunication preferredMeansOfCommunication;
    private String phoneNumber;
    private String email;

    private TenantStatus status;
    private String rejectedReason;
    private String login;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

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
}

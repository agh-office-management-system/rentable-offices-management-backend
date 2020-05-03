package pl.edu.agh.rentableoffices.office.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.common.EntityBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "office_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OfficeHistory extends EntityBase {

    private OfficeHistoryType type;

    private String additionalInfo;

    private LocalDateTime createdAt;

    //TODO createdBy uzupełnić
    private String createdBy;

    public static OfficeHistory created() {
        return new OfficeHistory(OfficeHistoryType.CREATED, null, LocalDateTime.now(), null);
    }

    //TODO payload
    public static OfficeHistory updated() {
        return new OfficeHistory(OfficeHistoryType.UPDATED, null, LocalDateTime.now(), null);
    }

    public static OfficeHistory tenantAssigned() {
        return new OfficeHistory(OfficeHistoryType.TENANT_ASSIGNED, null, LocalDateTime.now(), null);
    }

    public static OfficeHistory tenantRemoved() {
        return new OfficeHistory(OfficeHistoryType.TENANT_REMOVED, null, LocalDateTime.now(), null);
    }

    public static OfficeHistory repairCompleted() {
        return new OfficeHistory(OfficeHistoryType.REPAIR_COMPLETED, null, LocalDateTime.now(), null);
    }
}

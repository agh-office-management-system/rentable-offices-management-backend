package pl.edu.agh.rentableoffices.office.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import pl.edu.agh.rentableoffices.common.EntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "office_history")
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OfficeHistory extends EntityBase {

    @ManyToOne
    @JoinColumn(name="office_id", nullable = false)
    private Office office;

    @NotNull
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private OfficeHistoryType type;

    @Column(name="additional_info")
    private String additionalInfo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //TODO createdBy uzupełnić
    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    public static OfficeHistory created(Office office) {
        return new OfficeHistory(office, OfficeHistoryType.CREATED, null, LocalDateTime.now(), null);
    }

    //TODO payload
    public static OfficeHistory updated(Office office) {
        return new OfficeHistory(office, OfficeHistoryType.UPDATED, null, LocalDateTime.now(), null);
    }

    public static OfficeHistory tenantAssigned(Office office) {
        return new OfficeHistory(office, OfficeHistoryType.TENANT_ASSIGNED, null, LocalDateTime.now(), null);
    }

    public static OfficeHistory tenantRemoved(Office office) {
        return new OfficeHistory(office, OfficeHistoryType.TENANT_REMOVED, null, LocalDateTime.now(), null);
    }

    public static OfficeHistory repairCompleted(Office office) {
        return new OfficeHistory(office, OfficeHistoryType.REPAIR_COMPLETED, null, LocalDateTime.now(), null);
    }
}

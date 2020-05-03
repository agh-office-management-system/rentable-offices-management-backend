package pl.edu.agh.rentableoffices.tenant.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VerifyTenantCommand {
    private boolean accepted;
    private String rejectionReason;
}

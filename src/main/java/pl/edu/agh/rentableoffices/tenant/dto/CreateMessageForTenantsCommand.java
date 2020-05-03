package pl.edu.agh.rentableoffices.tenant.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateMessageForTenantsCommand {
    List<Long> tenantIds;
    String content;
    String from;
}

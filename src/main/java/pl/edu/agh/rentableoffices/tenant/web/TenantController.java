package pl.edu.agh.rentableoffices.tenant.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.office.exception.MaxOfficeCapacityReachedException;
import pl.edu.agh.rentableoffices.tenant.dto.*;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.service.CreateTenantService;
import pl.edu.agh.rentableoffices.tenant.service.TenantDetailsService;
import pl.edu.agh.rentableoffices.tenant.service.TenantMessageService;
import pl.edu.agh.rentableoffices.tenant.service.TenantUpdateService;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {
    private final CreateTenantService createTenantService;
    private final TenantDetailsService tenantDetailsService;
    private final TenantUpdateService tenantUpdateService;
    private final TenantMessageService tenantMessageService;

    @PostMapping(produces = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    public ResponseDto<Long> create(@RequestBody CreateTenantCommand command) {
        return ResponseDto.success(createTenantService.create(command));
    }

    @PostMapping("/message")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    public ResponseDto<Void> createMessageForTenants(@RequestBody CreateMessageForTenantsCommand command) {
        tenantMessageService.createMessageForTenants(command);
        return ResponseDto.success();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATION_EMPLOYEE', 'ROLE_SYSTEM_ADMIN') or #id == authentication.principal.tenantId")
    public ResponseDto<TenantDto> get(@PathVariable Long id) throws TenantNotFoundException {
        return ResponseDto.success(tenantDetailsService.get(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATION_EMPLOYEE', 'ROLE_SYSTEM_ADMIN') or #id == authentication.principal.tenantId")
    public ResponseDto<Void> update(@PathVariable Long id, @RequestBody UpdateTenantCommand command) throws TenantNotFoundException, MaxOfficeCapacityReachedException {
        tenantUpdateService.update(id, command);
        return ResponseDto.success();
    }

    @PutMapping("/{id}/verify")
    @PreAuthorize("hasRole('ROLE_TENANT') and #id == authentication.principal.tenantId")
    public ResponseDto<Void> verify(@PathVariable Long id, @RequestBody VerifyTenantCommand command) throws TenantNotFoundException {
        tenantUpdateService.verify(id, command);
        return ResponseDto.success();
    }
}

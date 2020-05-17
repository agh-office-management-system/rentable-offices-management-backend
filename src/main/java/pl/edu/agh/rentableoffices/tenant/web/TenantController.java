package pl.edu.agh.rentableoffices.tenant.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.office.exception.MaxOfficeCapacityReachedException;
import pl.edu.agh.rentableoffices.tenant.dto.*;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.service.*;

@RestController
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
public class TenantController {
    private final CreateTenantService createTenantService;
    private final TenantDetailsService tenantDetailsService;
    private final TenantUpdateService tenantUpdateService;
    private final TenantMessageService tenantMessageService;

    @PostMapping
    //TODO  Pracownik administracji
    public ResponseDto<Long> create(@RequestBody CreateTenantCommand command) {
        return ResponseDto.success(createTenantService.create(command));
    }

    //TODO  Pracownik administracji
    @PostMapping("/message")
    public ResponseDto<Void> createMessageForTenants(@RequestBody CreateMessageForTenantsCommand command) {
        tenantMessageService.createMessageForTenants(command);
        return ResponseDto.success();
    }

    @GetMapping("/{id}")
    public ResponseDto<TenantDto> get(@PathVariable Long id) throws TenantNotFoundException {
        return ResponseDto.success(tenantDetailsService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseDto<Void> update(@PathVariable Long id, @RequestBody UpdateTenantCommand command) throws TenantNotFoundException, MaxOfficeCapacityReachedException {
        tenantUpdateService.update(id, command);
        return ResponseDto.success();
    }

    @PutMapping("/{id}/verify")
    //TODO - Tenant
    public ResponseDto<Void> verify(@PathVariable Long id, @RequestBody VerifyTenantCommand command) throws TenantNotFoundException {
        tenantUpdateService.verify(id, command);
        return ResponseDto.success();
    }
}

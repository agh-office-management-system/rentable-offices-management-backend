package pl.edu.agh.rentableoffices.tenant.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.configuration.SwaggerTags;
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
@Api(value = "Endpointy dla najemców", tags = SwaggerTags.TENANT)
public class TenantController {
    private final CreateTenantService createTenantService;
    private final TenantDetailsService tenantDetailsService;
    private final TenantUpdateService tenantUpdateService;
    private final TenantMessageService tenantMessageService;

    @PostMapping(produces = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    @ApiOperation("Utworzenie najemcy")
    public ResponseDto<Long> create(@ApiParam("Żądanie utworzenia najemcy") @RequestBody CreateTenantCommand command) {
        return ResponseDto.success(createTenantService.create(command));
    }

    @PostMapping("/message")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    @ApiOperation("Utworzenie wiadomości dla najemców")
    public ResponseDto<Void> createMessageForTenants(@ApiParam("Żądanie utworzenia wiadomości") @RequestBody CreateMessageForTenantsCommand command) {
        tenantMessageService.createMessageForTenants(command);
        return ResponseDto.success();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATION_EMPLOYEE', 'ROLE_SYSTEM_ADMIN') or #id == authentication.principal.tenantId")
    @ApiOperation("Pobranie szczegółów najemcy")
    public ResponseDto<TenantDto> get(@ApiParam("Id najemcy") @PathVariable Long id) throws TenantNotFoundException {
        return ResponseDto.success(tenantDetailsService.get(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRATION_EMPLOYEE', 'ROLE_SYSTEM_ADMIN') or #id == authentication.principal.tenantId")
    @ApiOperation("Uaktualnienie danych najemcy")
    public ResponseDto<Void> update(@ApiParam("Id najemcy") @PathVariable Long id,@ApiParam("Żądanie uaktualnienia najemcy") @RequestBody UpdateTenantCommand command) throws TenantNotFoundException, MaxOfficeCapacityReachedException {
        tenantUpdateService.update(id, command);
        return ResponseDto.success();
    }

    @PutMapping("/{id}/verify")
    @PreAuthorize("hasRole('ROLE_TENANT') and #id == authentication.principal.tenantId")
    @ApiOperation("Weryfikacja najemcy")
    public ResponseDto<Void> verify(@ApiParam("Id najemcy")@PathVariable Long id,@ApiParam("Żądanie weryfikacji najemcy") @RequestBody VerifyTenantCommand command) throws TenantNotFoundException {
        tenantUpdateService.verify(id, command);
        return ResponseDto.success();
    }
}

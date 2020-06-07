package pl.edu.agh.rentableoffices.office.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.configuration.SwaggerTags;
import pl.edu.agh.rentableoffices.office.dto.*;
import pl.edu.agh.rentableoffices.office.exception.AddressAlreadyExistsException;
import pl.edu.agh.rentableoffices.office.exception.MaxOfficeCapacityReachedException;
import pl.edu.agh.rentableoffices.office.exception.OfficeNotFoundException;
import pl.edu.agh.rentableoffices.office.service.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/office")
@RequiredArgsConstructor
@Api(value = "Endpointy do zarządzania biurami", tags = SwaggerTags.OFFICE)
public class OfficeController {
    private final OfficeCreateService officeCreateService;
    private final OfficeUpdateService officeUpdateService;
    private final OfficeDetailsService officeDetailsService;
    private final OfficeHistoryService officeHistoryService;
    private final OfficeReportService officeReportService;

    @GetMapping("/{id}")
    @ApiOperation("Pobranie szczegółów biura")
    public ResponseDto<OfficeDto> get(@ApiParam(value = "Id biura", example = "1") @PathVariable Long id) {
        return ResponseDto.success(officeDetailsService.get(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    @ApiOperation("Utworzenie biura")
    public ResponseDto<Long> create(@ApiParam("Żądanie utworzenia biura") @RequestBody @Valid CreateOfficeCommand request) throws AddressAlreadyExistsException {
        return ResponseDto.success(officeCreateService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    @ApiOperation("Uaktualnienie danych biura")
    public ResponseDto<Void> update(@ApiParam(value = "Id biura", example = "1") @PathVariable Long id, @ApiParam("Żądanie uaktualnienia danych biura") @RequestBody @Valid UpdateOfficeCommand request)
            throws OfficeNotFoundException {
        officeUpdateService.update(id, request);
        return ResponseDto.success();
    }

    @PutMapping("/{id}/tenant/{tenantId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    @ApiOperation("Przypisanie najemcy do biura")
    public ResponseDto<Void> assignTenant(@ApiParam(value = "Id biura", example = "1") @PathVariable Long id, @ApiParam(value = "Id najemcy", example = "1") @PathVariable Long tenantId)
            throws TenantNotFoundException, OfficeNotFoundException, MaxOfficeCapacityReachedException {
        officeUpdateService.assignTenant(id, tenantId);
        return ResponseDto.success();
    }

    @DeleteMapping("/{id}/tenant/{tenantId}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    @ApiOperation("Usunięcie najemcy z biura")
    public ResponseDto<Void> removeTenant(@ApiParam(value = "Id biura", example = "1") @PathVariable Long id, @ApiParam(value = "Id najemcy", example = "1") @PathVariable Long tenantId)
            throws OfficeNotFoundException, TenantNotFoundException {
        officeUpdateService.removeTenant(id, tenantId);
        return ResponseDto.success();
    }

    @GetMapping("/report")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    @ApiOperation("Utworzenie raportu zajętości biur")
    public ResponseDto<List<OfficeReportDto>> generateOfficeReport() {
        return ResponseDto.success(officeReportService.createOfficeReport());
    }

    @GetMapping("/report/{id}")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    @ApiOperation("Utworzenie raportu z historią biura")
    public ResponseDto<List<OfficeHistoryDto>> generateOfficeHistoryReport(@ApiParam(value = "Id biura", example = "1") @PathVariable Long id) throws OfficeNotFoundException {
        return ResponseDto.success(this.officeHistoryService.getOfficeHistory(id));
    }

}

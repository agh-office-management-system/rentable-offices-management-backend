package pl.edu.agh.rentableoffices.office.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.office.dto.*;
import pl.edu.agh.rentableoffices.office.exception.OfficeNotFoundException;
import pl.edu.agh.rentableoffices.office.service.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;

import java.util.List;

@RestController
@RequestMapping("/api/apartment")
@RequiredArgsConstructor
public class OfficeController {
    private final OfficeCreateService officeCreateService;
    private final OfficeUpdateService officeUpdateService;
    private final OfficeDetailsService officeDetailsService;
    private final OfficeHistoryService officeHistoryService;
    private final OfficeReportService officeReportService;

    @GetMapping("/{id}")
    public ResponseDto<OfficeDto> get(@PathVariable Long id) {
        return ResponseDto.success(officeDetailsService.get(id));
    }

    @PostMapping
    //TODO - Pracownik administracji
    public ResponseDto<Long> create(@RequestBody CreateOfficeCommand request) {
        return ResponseDto.success(officeCreateService.create(request));
    }

    @PutMapping("/{id}")
    //TODO - Pracownik administracji
    //TODO - Jeżeli lokal jest zajmowany przez najemcę, informacja jest przekazywana do najemcy.
    public ResponseDto<Void> update(@PathVariable Long id, @RequestBody UpdateOfficeCommand request) {
        officeUpdateService.update(id, request);
        return ResponseDto.success();
    }

    @PutMapping("/{id}/tenant/{tenantId}")
    //TODO - Pracownik administracji
    public ResponseDto<Void> assignTenant(@PathVariable Long id, @PathVariable Long tenantId) {
        officeUpdateService.assignTenant(id, tenantId);
        return ResponseDto.success();
    }

    @GetMapping("/report")
    //TODO Pracownik administracji
    public ResponseDto<List<OfficeReportDto>> generateOfficeReport() {
        return ResponseDto.success(officeReportService.createOfficeReport());
    }

    @GetMapping("/report/{id}")
    //TODO Pracownik administracji
    public ResponseDto<List<OfficeHistoryDto>> generateOfficeHistoryReport(@PathVariable Long id) throws OfficeNotFoundException {
        return ResponseDto.success(this.officeHistoryService.getOfficeHistory(id));
    }

}

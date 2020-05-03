package pl.edu.agh.rentableoffices.tenant.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.tenant.dto.*;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.service.*;

@RestController
@RequestMapping("/api/tenant")
@RequiredArgsConstructor
public class TenantController {
    private CreateTenantService createTenantService;
    private TenantDetailsService tenantDetailsService;
    private TenantUpdateService tenantUpdateService;
    private TenantMessageService tenantMessageService;
    private TenantSurveyService tenantSurveyService;

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
    public ResponseDto<TenantDto> get(@PathVariable Long id) {
        return ResponseDto.success(tenantDetailsService.get(id));
    }

    @PutMapping("/{id}")
    public ResponseDto<Void> update(@PathVariable Long id, @RequestBody UpdateTenantCommand command) throws TenantNotFoundException {
        tenantUpdateService.update(id, command);
        return ResponseDto.success();
    }

    //TODO landlord
    @PutMapping("/{id}/verify")
    public ResponseDto<Void> verify(@PathVariable Long id, @RequestBody VerifyTenantCommand command) throws TenantNotFoundException {
        tenantUpdateService.verify(id, command);
        return ResponseDto.success();
    }

    @PostMapping("/survey")
    //TODO Pracownik administracji
    public ResponseDto<Long> createSurvey(@RequestBody CreateSurveyCommand command){
        return ResponseDto.success(tenantSurveyService.createSurvey(command));
    }

    @PostMapping("/survey/{id}")
    public ResponseDto<Void> submitSurvey(@PathVariable Long id, @RequestBody SubmitSurveyCommand command) {
        tenantSurveyService.submitSurvey(id, command);
        return ResponseDto.success();
    }

    @PostMapping("/survey/{id}/reject")
    public ResponseDto<Void> rejectSurvey(@PathVariable Long id) {
        tenantSurveyService.rejectSurvey(id);
        return ResponseDto.success();
    }


}

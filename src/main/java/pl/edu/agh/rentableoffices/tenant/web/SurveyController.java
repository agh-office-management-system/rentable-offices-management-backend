package pl.edu.agh.rentableoffices.tenant.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.tenant.dto.survey.CreateSurveyCommand;
import pl.edu.agh.rentableoffices.tenant.dto.survey.SubmitSurveyCommand;
import pl.edu.agh.rentableoffices.tenant.dto.survey.SurveyDto;
import pl.edu.agh.rentableoffices.tenant.exception.SurverAnswersNotCompleteException;
import pl.edu.agh.rentableoffices.tenant.exception.SurveyNotFoundException;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.service.TenantSurveyService;

@RestController
@RequestMapping("/api/tenant/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final TenantSurveyService tenantSurveyService;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    public ResponseDto<Long> createSurvey(@RequestBody CreateSurveyCommand command){
        return ResponseDto.success(tenantSurveyService.createSurvey(command));
    }

    @GetMapping("/{id}")
    public ResponseDto<SurveyDto> getSurvey(@PathVariable Long id) throws SurveyNotFoundException {
        return ResponseDto.success(tenantSurveyService.getSurvey(id));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_TENANT')")
    public ResponseDto<Void> submitSurvey(@PathVariable Long id, @RequestBody SubmitSurveyCommand command)
            throws SurveyNotFoundException, TenantNotFoundException, SurverAnswersNotCompleteException {
        tenantSurveyService.submitSurveyAnswer(id, command);
        return ResponseDto.success();
    }

    @PostMapping("/{id}/reject/{tenantId}")
    @PreAuthorize("hasRole('ROLE_TENANT')")
    public ResponseDto<Void> rejectSurvey(@PathVariable Long id, @PathVariable Long tenantId)
            throws SurveyNotFoundException, TenantNotFoundException {
        tenantSurveyService.rejectSurvey(id, tenantId);
        return ResponseDto.success();
    }
}

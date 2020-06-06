package pl.edu.agh.rentableoffices.tenant.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.rentableoffices.common.ResponseDto;
import pl.edu.agh.rentableoffices.configuration.SwaggerTags;
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
@Api(value = "Endpointy dla ankiet",tags = SwaggerTags.SURVEY)
public class SurveyController {
    private final TenantSurveyService tenantSurveyService;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMINISTRATION_EMPLOYEE')")
    @ApiOperation("Utworzenie ankiety")
    public ResponseDto<Long> createSurvey(@ApiParam("Żądanie utworzenia ankiety") @RequestBody CreateSurveyCommand command){
        return ResponseDto.success(tenantSurveyService.createSurvey(command));
    }

    @GetMapping("/{id}")
    @ApiOperation("Pobranie ankiety")
    public ResponseDto<SurveyDto> getSurvey(@ApiParam("Id ankiety") @PathVariable Long id) throws SurveyNotFoundException {
        return ResponseDto.success(tenantSurveyService.getSurvey(id));
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_TENANT')")
    @ApiOperation("Udzielenie odpowiedzi na ankietę")
    public ResponseDto<Void> submitSurvey(@ApiParam("Id ankiety") @PathVariable Long id, @ApiParam("Żądanie odpowiedzi na ankietę")  @RequestBody SubmitSurveyCommand command)
            throws SurveyNotFoundException, TenantNotFoundException, SurverAnswersNotCompleteException {
        tenantSurveyService.submitSurveyAnswer(id, command);
        return ResponseDto.success();
    }

    @PostMapping("/{id}/reject/{tenantId}")
    @PreAuthorize("hasRole('ROLE_TENANT')")
    @ApiOperation("Odrzucenie ankiety")
    public ResponseDto<Void> rejectSurvey(@ApiParam("Id ankiety") @PathVariable Long id,@ApiParam("Id najemcy")  @PathVariable Long tenantId)
            throws SurveyNotFoundException, TenantNotFoundException {
        tenantSurveyService.rejectSurvey(id, tenantId);
        return ResponseDto.success();
    }
}

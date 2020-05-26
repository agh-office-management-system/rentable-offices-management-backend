package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.common.BusinessRuntimeException;
import pl.edu.agh.rentableoffices.messaging.service.NotificationCreateService;
import pl.edu.agh.rentableoffices.tenant.dao.SurveyAnswerRepository;
import pl.edu.agh.rentableoffices.tenant.dao.SurveyRepository;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.dto.survey.CreateSurveyCommand;
import pl.edu.agh.rentableoffices.tenant.dto.survey.SubmitSurveyCommand;
import pl.edu.agh.rentableoffices.tenant.dto.survey.SurveyDto;
import pl.edu.agh.rentableoffices.tenant.exception.SurverAnswersNotCompleteException;
import pl.edu.agh.rentableoffices.tenant.exception.SurveyNotFoundException;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.mapper.SurveyMapper;
import pl.edu.agh.rentableoffices.tenant.model.survey.Survey;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;
import pl.edu.agh.rentableoffices.tenant.model.survey.SurveyAnswer;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TenantSurveyService {
    private final SurveyRepository repository;
    private final TenantRepository tenantRepository;
    private final SurveyAnswerRepository surveyAnswerRepository;
    private final SurveyMapper mapper;
    private final NotificationCreateService notificationCreateService;

    public Long createSurvey(@NotNull CreateSurveyCommand command) {
        List<Tenant> tenants = tenantRepository.findAllById(command.getTenantIds());
        Survey survey = Survey.create(command.getName(), command.getDescription(),
                command.getQuestions(), tenants);
        survey = repository.save(survey);
        log.info("Survey \"{}\" created.",command.getName());
        notificationCreateService.createSurveyCreatedNotification(tenants.stream().map(Tenant::getEmail).collect(Collectors.toList()), survey.getId());

        return survey.getId();
    }

    public SurveyDto getSurvey(@NotNull Long id) throws SurveyNotFoundException {
        return mapper.toDto(repository.get(id));
    }

    //TODO -> create report or get all answers
    public void submitSurveyAnswer(@NotNull Long id, SubmitSurveyCommand command)
            throws SurveyNotFoundException, TenantNotFoundException, SurverAnswersNotCompleteException {
        Survey survey = repository.get(id);
        Tenant tenant = tenantRepository.get(command.getTenantId());

        if(!survey.getTarget().contains(tenant)) {
            throw new BusinessRuntimeException("TENANT_NOT_ASSIGNED_TO_SURVEY");
        }

        if(surveyAnswerRepository.existsByTenantIdAndSurveyId(tenant.getId(), survey.getId())) {
            throw new BusinessRuntimeException("SURVEY_ALREADY_ANSWERED");
        }

        SurveyAnswer surveyAnswer = SurveyAnswer.create(tenant, survey, command.getAnswers());
        surveyAnswer = surveyAnswerRepository.save(surveyAnswer);
        log.info("Survey \"{}\" answered by {}", survey.getName(), tenant.getFullName());
        notificationCreateService.createSurveyAnswerSubmittedNotification(tenant.getEmail(), surveyAnswer.getId());
    }

    public void getSurveyResults(@NotNull Long id) {
        List<SurveyAnswer> survey = surveyAnswerRepository.getAllBySurveyId(id);
        //survey.getA
    }

    public void rejectSurvey(@NotNull Long id, @NotNull Long tenantId)
            throws SurveyNotFoundException, TenantNotFoundException {
        Survey survey = repository.get(id);
        Tenant tenant = tenantRepository.get(tenantId);

        if(!survey.getTarget().contains(tenant)) {
            throw new BusinessRuntimeException("TENANT_NOT_ASSIGNED_TO_SURVEY");
        }
        if(surveyAnswerRepository.existsByTenantIdAndSurveyId(tenant.getId(), survey.getId())) {
            throw new BusinessRuntimeException("SURVEY_ALREADY_ANSWERED");
        }

        SurveyAnswer surveyAnswer = SurveyAnswer.reject(tenant, survey);
        surveyAnswerRepository.save(surveyAnswer);
        log.info("Survey \"{}\" rejected by {}", survey.getName(), tenant.getFullName());
        notificationCreateService.createSurveyAnswerRejectedNotification(tenant.getEmail());
    }

}

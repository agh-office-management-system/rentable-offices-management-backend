package pl.edu.agh.rentableoffices.tenant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.agh.rentableoffices.messaging.model.NotificationType;
import pl.edu.agh.rentableoffices.messaging.service.NotificationService;
import pl.edu.agh.rentableoffices.tenant.dao.SurveyAnswerRepository;
import pl.edu.agh.rentableoffices.tenant.dao.SurveyRepository;
import pl.edu.agh.rentableoffices.tenant.dao.TenantRepository;
import pl.edu.agh.rentableoffices.tenant.dto.survey.CreateSurveyCommand;
import pl.edu.agh.rentableoffices.tenant.dto.survey.SubmitSurveyCommand;
import pl.edu.agh.rentableoffices.tenant.dto.survey.SurveyDto;
import pl.edu.agh.rentableoffices.tenant.exception.SurveyNotFoundException;
import pl.edu.agh.rentableoffices.tenant.exception.TenantNotFoundException;
import pl.edu.agh.rentableoffices.tenant.mapper.SurveyMapper;
import pl.edu.agh.rentableoffices.tenant.model.survey.Survey;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;
import pl.edu.agh.rentableoffices.tenant.model.survey.SurveyAnswer;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TenantSurveyService {
    private final SurveyRepository repository;
    private final TenantRepository tenantRepository;
    private final SurveyAnswerRepository surveyAnswerRepository;
    private final SurveyMapper mapper;
    private final NotificationService notificationService;

    public Long createSurvey(@NotNull CreateSurveyCommand command) {
        List<Tenant> tenants = tenantRepository.findAllById(command.getTenantIds());
        Survey survey = Survey.create(command.getName(), command.getDescription(),
                command.getQuestions(), tenants);
        survey = repository.save(survey);
        notificationService.notifyTenants(tenants, NotificationType.SURVEY_CREATED, new Object[]{survey.getId()});
        log.info("Survey \"{}\" created.",command.getName());
        return survey.getId();
    }

    public SurveyDto getSurvey(@NotNull Long id) throws SurveyNotFoundException {
        return mapper.toDto(repository.get(id));
    }

    //TODO -> create report
    public void submitSurveyAnswer(@NotNull Long id, SubmitSurveyCommand command)
            throws SurveyNotFoundException, TenantNotFoundException {
        Survey survey = repository.get(id);
        Tenant tenant = tenantRepository.get(command.getTenantId());
        SurveyAnswer surveyAnswer = SurveyAnswer.create(tenant, survey, command.getAnswers());
        surveyAnswer = surveyAnswerRepository.save(surveyAnswer);
        notificationService.notifyAdministration(NotificationType.SURVEY_ANSWER_SUBMITTED, new Object[]{surveyAnswer.getId()});
    }

    public void rejectSurvey(@NotNull Long id, @NotNull Long tenantId)
            throws SurveyNotFoundException, TenantNotFoundException {
        Survey survey = repository.get(id);
        Tenant tenant = tenantRepository.get(tenantId);
        SurveyAnswer surveyAnswer = SurveyAnswer.reject(tenant, survey);
        surveyAnswerRepository.save(surveyAnswer);
        notificationService.notifyAdministration(NotificationType.SURVEY_ANSWER_REJECTED, new Object[]{surveyAnswer.getId()});
    }
}

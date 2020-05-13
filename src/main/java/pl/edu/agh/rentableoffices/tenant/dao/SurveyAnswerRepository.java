package pl.edu.agh.rentableoffices.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.rentableoffices.tenant.model.survey.SurveyAnswer;

public interface SurveyAnswerRepository extends JpaRepository<SurveyAnswer, Long> {
    boolean existsByTenantIdAndSurveyId(Long tenantId, Long surveyId);
}

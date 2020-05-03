package pl.edu.agh.rentableoffices.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.rentableoffices.tenant.model.survey.Survey;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findByQuestionCodeAndQuestionSurvey(String code, Survey survey);
}

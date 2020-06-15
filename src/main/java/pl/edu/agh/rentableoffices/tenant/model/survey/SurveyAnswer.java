package pl.edu.agh.rentableoffices.tenant.model.survey;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.common.BusinessRuntimeException;
import pl.edu.agh.rentableoffices.common.EntityBase;
import pl.edu.agh.rentableoffices.tenant.dto.survey.answer.AnswerDto;
import pl.edu.agh.rentableoffices.tenant.exception.SurverAnswersNotCompleteException;
import pl.edu.agh.rentableoffices.tenant.model.Tenant;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.Answer;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "survey_answer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SurveyAnswer extends EntityBase {
    @OneToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @OneToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers;

    private boolean answered;

    public static SurveyAnswer create(Tenant tenant, Survey survey, Set<AnswerDto> answers) throws SurverAnswersNotCompleteException {
        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.survey = survey;
        surveyAnswer.tenant = tenant;
        surveyAnswer.answers = answers.stream()
                .map(a -> AnswerCreator.create(findQuestion(survey, a.getCode()), a))
                .collect(Collectors.toSet());

        if(!allRequiredAnswered(survey, answers)) {
            throw new SurverAnswersNotCompleteException();
        }

        surveyAnswer.answered = true;
        return surveyAnswer;
    }

    public static SurveyAnswer reject(Tenant tenant, Survey survey) {
        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.survey = survey;
        surveyAnswer.tenant = tenant;
        surveyAnswer.answered = false;
        return surveyAnswer;
    }

    private static boolean allRequiredAnswered(Survey survey, Set<AnswerDto> answers) {
        return survey.getQuestions()
                .stream()
                .filter(Question::isRequired)
                .allMatch(q -> answers.stream().anyMatch(a -> a.getCode().equalsIgnoreCase(q.getCode())));
    }

    private static Question findQuestion(Survey survey, String code) {
        return survey.getQuestions().stream().filter(q -> q.getCode().equalsIgnoreCase(code)).findFirst()
                .orElseThrow(() -> new BusinessRuntimeException("QUESTION_DOES_NOT_EXIST"));
    }
}

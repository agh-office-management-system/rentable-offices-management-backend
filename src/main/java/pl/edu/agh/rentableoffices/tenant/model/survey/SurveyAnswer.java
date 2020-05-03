package pl.edu.agh.rentableoffices.tenant.model.survey;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.common.EntityBase;
import pl.edu.agh.rentableoffices.tenant.dto.survey.AnswerDto;
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

    @OneToMany(mappedBy = "surveyAnswer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers;

    private boolean answered;

    public static SurveyAnswer create(Tenant tenant, Survey survey, Set<AnswerDto> answerDtos) {
        SurveyAnswer surveyAnswer = new SurveyAnswer();
        surveyAnswer.survey = survey;
        surveyAnswer.tenant = tenant;
        surveyAnswer.answers = answerDtos.stream()
                .map(a -> AnswerCreator.create(findQuestion(survey, a.getQuestionCode()), a))
                .collect(Collectors.toSet());
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

    private static Question findQuestion(Survey survey, String code) {
        return survey.getQuestions().stream().filter(q -> q.getCode().equalsIgnoreCase(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Question does not exist in survey"));
    }
}

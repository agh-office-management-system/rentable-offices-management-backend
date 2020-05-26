package pl.edu.agh.rentableoffices.tenant.model.survey;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.tenant.dto.survey.answer.AnswerDto;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.Answer;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.BooleanAnswer;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.RangeAnswer;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.StringAnswer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerCreator {
    public static <T> Answer<T> create(Question question, AnswerDto<T> dto) {
        switch (question.getType()) {
            case STRING:
                return (Answer<T>) StringAnswer.create(question, (String) dto.getValue());
            case BOOLEAN:
                return (Answer<T>) BooleanAnswer.create(question, (Boolean) dto.getValue());
            case RANGE:
                return (Answer<T>) RangeAnswer.create((RangeQuestion) question, (Integer) dto.getValue());
            default:
                throw new IllegalArgumentException();
        }
    }
}

package pl.edu.agh.rentableoffices.tenant.model.survey;

import pl.edu.agh.rentableoffices.tenant.dto.survey.AnswerDto;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.Answer;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.BooleanAnswer;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.RangeAnswer;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.StringAnswer;

public class AnswerCreator {
    public static Answer create(Question question, AnswerDto dto) {
        switch (question.getType()) {
            case STRING:
                return StringAnswer.create(question, (String) dto.getValue());
            case BOOLEAN:
                return BooleanAnswer.create(question, (Boolean) dto.getValue());
            case RANGE:
                return RangeAnswer.create(question, (Integer) dto.getValue());
            default:
                throw new IllegalArgumentException();
        }
    }
}

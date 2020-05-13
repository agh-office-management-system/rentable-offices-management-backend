package pl.edu.agh.rentableoffices.tenant.model.survey;

import pl.edu.agh.rentableoffices.tenant.dto.survey.QuestionDto;
import pl.edu.agh.rentableoffices.tenant.dto.survey.RangeQuestionDto;

public class QuestionCreator {
    public static Question create(Survey survey, QuestionDto dto) {
        switch (dto.getType()) {
            case "STRING" :
                return StringQuestion.create(dto.getCode(), dto.getValue(), dto.isRequired());
            case "BOOLEAN" :
                return BooleanQuestion.create(dto.getCode(), dto.getValue(), dto.isRequired());
            case "RANGE" :
                return RangeQuestion.create(dto.getCode(), dto.getValue(), dto.isRequired(),((RangeQuestionDto) dto).getMin(), ((RangeQuestionDto) dto).getMax());
            default:
                throw new IllegalStateException("Unknown question type");
        }
    }
}

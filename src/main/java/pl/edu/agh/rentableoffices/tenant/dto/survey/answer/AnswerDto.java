package pl.edu.agh.rentableoffices.tenant.dto.survey.answer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import pl.edu.agh.rentableoffices.tenant.model.survey.answer.Answer;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RangeAnswerDto.class, name = "RANGE"),
        @JsonSubTypes.Type(value = BooleanAnswerDto.class, name = "BOOLEAN"),
        @JsonSubTypes.Type(value = StringAnswerDto.class, name = "STRING"),
})
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("Zasób reprezentujący odpowiedź na pytanie ankiety")
public abstract class AnswerDto<T> {
    @ApiModelProperty("Kod pytania")
    protected String code;
    @ApiModelProperty("Odpowiedź")
    protected T value;

    public static <T> AnswerDto<T> create(Answer<T> q) {
        switch (q.getQuestion().getType()) {
            case RANGE:
                return (AnswerDto<T>) new RangeAnswerDto(q.getQuestion().getCode(), (Integer) q.getValue());
            case BOOLEAN:
                return (AnswerDto<T>) new BooleanAnswerDto(q.getQuestion().getCode(), (Boolean) q.getValue());
            case STRING:
                return (AnswerDto<T>) new StringAnswerDto(q.getQuestion().getCode(), (String) q.getValue());
            default:
                throw new IllegalStateException("Unknown question type");
        }
    }
}

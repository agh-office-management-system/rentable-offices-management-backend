package pl.edu.agh.rentableoffices.tenant.dto.survey.question;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import pl.edu.agh.rentableoffices.tenant.model.survey.Question;
import pl.edu.agh.rentableoffices.tenant.model.survey.RangeQuestion;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RangeQuestionDto.class, name = "RANGE"),
        @JsonSubTypes.Type(value = BooleanQuestionDto.class, name = "BOOLEAN"),
        @JsonSubTypes.Type(value = StringQuestionDto.class, name = "STRING"),
})
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("Zasób reprezentujący pytanie w ankiecie")
public abstract class QuestionDto {
    @ApiModelProperty("Kod pytania")
    protected String code;

    @ApiModelProperty("Treść pytania")
    protected String value;

    @ApiModelProperty("Czy odpowiedź jest wymagana?")
    private boolean required = false;

    @JsonProperty("type")
    public abstract String getType();

    public static QuestionDto create(Question q) {
        switch (q.getType()) {
            case RANGE:
                return new RangeQuestionDto(q.getCode(), q.getValue(), ((RangeQuestion) q).getMin(), ((RangeQuestion) q).getMax());
            case BOOLEAN:
                return new BooleanQuestionDto(q.getCode(), q.getValue());
            case STRING:
                return new StringQuestionDto(q.getCode(), q.getValue());
            default:
                throw new IllegalStateException("Unknown question type");
        }
    }
}

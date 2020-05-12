package pl.edu.agh.rentableoffices.tenant.dto.survey;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@JsonTypeName("RANGE")
public class RangeQuestionDto extends QuestionDto {
    private int min;
    private int max;

    @Override
    public String getType() {
        return "RANGE";
    }

    public RangeQuestionDto(String code, String value, int min, int max) {
        this.code = code;
        this.value = value;
        this.min = min;
        this.max = max;
    }
}

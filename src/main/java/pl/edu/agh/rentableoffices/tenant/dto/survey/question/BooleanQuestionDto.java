package pl.edu.agh.rentableoffices.tenant.dto.survey.question;


import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;

@JsonTypeName("Boolean")
@ApiModel(parent = QuestionDto.class, value = "Zasób reprezentujący pytanie tak/nie")
public class BooleanQuestionDto extends QuestionDto {
    @Override
    public String getType() {
        return "BOOLEAN";
    }

    public BooleanQuestionDto(String code, String value) {
        this.code = code;
        this.value = value;
    }
}

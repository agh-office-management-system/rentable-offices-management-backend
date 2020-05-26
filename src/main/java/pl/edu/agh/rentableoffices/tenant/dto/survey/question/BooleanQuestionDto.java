package pl.edu.agh.rentableoffices.tenant.dto.survey.question;


import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Boolean")
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

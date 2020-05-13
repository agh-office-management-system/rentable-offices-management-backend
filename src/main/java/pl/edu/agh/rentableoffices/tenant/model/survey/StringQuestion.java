package pl.edu.agh.rentableoffices.tenant.model.survey;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("STRING")
public class StringQuestion extends Question {
    @Override
    public QuestionType getType() {
        return QuestionType.STRING;
    }

    public static StringQuestion create(String code, String value, boolean required) {
        return new StringQuestion(code, value, required);
    }

    private StringQuestion(String code, String value, boolean required) {
        super(code, value, required);
    }
}

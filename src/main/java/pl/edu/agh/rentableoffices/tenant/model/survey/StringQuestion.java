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

    public static StringQuestion create(String code, String value) {
        return new StringQuestion(code, value);
    }

    private StringQuestion(String code, String value) {
        super(code, value);
    }
}

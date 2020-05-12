package pl.edu.agh.rentableoffices.tenant.model.survey;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("BOOLEAN")
public class BooleanQuestion extends Question {
    @Override
    public QuestionType getType() {
        return QuestionType.BOOLEAN;
    }

    public static BooleanQuestion create(String code, String value) {
        return new BooleanQuestion(code, value);
    }

    private BooleanQuestion(String code, String value) {
        super(code, value);
    }
}

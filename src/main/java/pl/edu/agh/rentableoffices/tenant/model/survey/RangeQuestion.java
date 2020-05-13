package pl.edu.agh.rentableoffices.tenant.model.survey;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DiscriminatorValue("RANGE")
public class RangeQuestion extends Question {
    private int min;
    private int max;

    public static RangeQuestion create(String code, String value, boolean required, int min, int max) {
        return new RangeQuestion(code, value, required, min, max);
    }

    private RangeQuestion(String code, String value, boolean required, int min, int max) {
        super(code, value, required);
        this.min = min;
        this.max = max;
    }

    @Override
    public QuestionType getType() {
        return QuestionType.RANGE;
    }
}

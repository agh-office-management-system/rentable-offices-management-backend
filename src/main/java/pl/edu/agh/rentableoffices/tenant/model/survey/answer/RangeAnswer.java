package pl.edu.agh.rentableoffices.tenant.model.survey.answer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.tenant.model.survey.RangeQuestion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("range")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RangeAnswer extends Answer<Integer> {
    @Column(name="range_value")
    private Integer value;

    public static RangeAnswer create(RangeQuestion question, Integer answer) {
        if(question.isRequired() && answer == null) {
            throw new IllegalArgumentException("Answer to question is required");
        }
        if(answer != null && (answer > question.getMax() || answer < question.getMin())) {
            throw new IllegalArgumentException("Answer is not within question boundaries");
        }
        RangeAnswer obj = new RangeAnswer();
        obj.question = question;
        obj.value = answer;
        return obj;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}

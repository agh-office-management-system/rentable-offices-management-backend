package pl.edu.agh.rentableoffices.tenant.model.survey.answer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.tenant.model.survey.Question;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//TODO range max and min
@Entity
@DiscriminatorValue("range")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RangeAnswer extends Answer<Integer> {
    @Column(name="range_value")
    private Integer value;

    public static RangeAnswer create(Question question, Integer answer) {
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

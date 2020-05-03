package pl.edu.agh.rentableoffices.tenant.model.survey.answer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.tenant.model.survey.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("range")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RangeAnswer extends Answer<Integer> {

    public static RangeAnswer create(Question question, Integer answer) {
        RangeAnswer obj = new RangeAnswer();
        obj.question = question;
        obj.value = answer;
        return obj;
    }
}

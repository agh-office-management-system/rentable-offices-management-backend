package pl.edu.agh.rentableoffices.tenant.model.survey.answer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.tenant.model.survey.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("boolean")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BooleanAnswer extends Answer<Boolean> {

    public static BooleanAnswer create(Question question, Boolean answer) {
        BooleanAnswer obj = new BooleanAnswer();
        obj.question = question;
        obj.value = answer;
        return obj;
    }
}

package pl.edu.agh.rentableoffices.tenant.model.survey.answer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.tenant.model.survey.Question;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("boolean")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BooleanAnswer extends Answer<Boolean> {
    @Column(name="boolean_value")
    private boolean value;

    public static BooleanAnswer create(Question question, Boolean answer) {
        BooleanAnswer obj = new BooleanAnswer();
        obj.question = question;
        obj.value = answer;
        return obj;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}

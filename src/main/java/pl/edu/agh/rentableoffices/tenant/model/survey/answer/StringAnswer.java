package pl.edu.agh.rentableoffices.tenant.model.survey.answer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.tenant.model.survey.Question;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("string")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StringAnswer extends Answer<String> {
    @Column(name="string_value")
    private String value;

    public static StringAnswer create(Question question, String answer) {
        StringAnswer obj = new StringAnswer();
        obj.question = question;
        obj.value = answer;
        return obj;
    }

    @Override
    public String getValue() {
        return value;
    }
}

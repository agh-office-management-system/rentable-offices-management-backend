package pl.edu.agh.rentableoffices.tenant.model.survey.answer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.tenant.model.survey.Question;
import pl.edu.agh.rentableoffices.tenant.model.survey.Survey;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("string")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StringAnswer extends Answer<String> {

    public static StringAnswer create(Question question, String answer) {
        StringAnswer obj = new StringAnswer();
        obj.question = question;
        obj.value = answer;
        return obj;
    }
}

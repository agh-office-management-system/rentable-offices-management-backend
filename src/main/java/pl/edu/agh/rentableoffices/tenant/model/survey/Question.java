package pl.edu.agh.rentableoffices.tenant.model.survey;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.agh.rentableoffices.common.EntityBase;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "survey")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Question extends EntityBase {
    @ManyToOne
    @JoinColumn(name="survey_id")
    private Survey survey;

    private String code;

    private String value;

    private QuestionType type;

    public static Question create(Survey survey, String code, String value, QuestionType type) {
        return new Question(survey,code,value,type);
    }
}

package pl.edu.agh.rentableoffices.tenant.model.survey;

import lombok.*;
import pl.edu.agh.rentableoffices.common.EntityBase;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class Question extends EntityBase {

    private String code;

    private String value;

    public abstract QuestionType getType();
}
